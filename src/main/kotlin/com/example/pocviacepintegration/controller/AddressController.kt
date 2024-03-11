package com.example.pocviacepintegration.controller

import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.controller.response.AddressResponse
import com.example.pocviacepintegration.exceptions.AddressAlreadyExistsException
import com.example.pocviacepintegration.exceptions.AddressNotFoundException
import com.example.pocviacepintegration.exceptions.CepFormatException
import com.example.pocviacepintegration.model.entities.AddressEntity
import com.example.pocviacepintegration.service.AddressServiceImpl
import com.example.pocviacepintegration.utils.FormatCep
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AddressController @Autowired constructor(
    private val addressService: AddressServiceImpl,
    private val formatCep: FormatCep
) {
    @Operation(summary = "Busca um andereço pelo CEP")
    @GetMapping("/{cep}")
    fun getAddressByCep(@PathVariable cep: String): ResponseEntity<AddressResponse> {
        try {
            return addressService.getAddress(formatCep.formatCep(cep))
        }catch (addressNotFoundException: AddressNotFoundException){
            throw AddressNotFoundException(cep)
        }
    }

    @Operation(summary = "Cria um novo endereço")
    @PostMapping
    fun createAddress(@RequestBody addressRequest: AddressRequest): ResponseEntity<AddressEntity> {
       val newAddressRequest =  addressRequest.copy(cep = formatCep.formatCep(addressRequest.cep))
        try {
            return addressService.saveAddress(newAddressRequest)
        }catch (adressAlreadyExistsException: AddressAlreadyExistsException){
            throw AddressAlreadyExistsException(newAddressRequest.cep)
        }
    }

    @Operation(summary = "Atualiza um endereço")
    @PutMapping
    fun updateAddress(@RequestBody addressRequest: AddressRequest): ResponseEntity<AddressEntity> {
        val newAddressRequest =  addressRequest.copy(cep = formatCep.formatCep(addressRequest.cep))
        try {
            return addressService.updateAddress(newAddressRequest)
        }catch (addressNotFoundException: AddressNotFoundException){
            throw AddressNotFoundException(newAddressRequest.cep)
        }
    }

    @Operation(summary = "Deleta um endereço")
    @DeleteMapping("/{cep}")
    fun deleteAddress(@PathVariable cep: String) : ResponseEntity<Unit>{
        try {
            return addressService.deleteAddress(formatCep.formatCep(cep))
        }catch (addressNotFoundException: AddressNotFoundException){
            throw AddressNotFoundException(cep)
        }
    }
}
