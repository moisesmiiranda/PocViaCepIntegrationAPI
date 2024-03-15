package com.example.pocviacepintegration.controller

import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.exceptions.AddressAlreadyExistsException
import com.example.pocviacepintegration.exceptions.AddressNotFoundException
import com.example.pocviacepintegration.exceptions.CepFormatException
import com.example.pocviacepintegration.exceptions.CepLengthException
import com.example.pocviacepintegration.model.entities.AddressEntity
import com.example.pocviacepintegration.service.AddressServiceImpl
import com.example.pocviacepintegration.utils.FormatCep
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/address")
class AddressController @Autowired constructor(
    private val addressService: AddressServiceImpl,
    private val formatCep: FormatCep,
) {

    @Operation(summary = "Busca um endereço pelo CEP")
    @GetMapping("/{cep}")
    fun getAddressByCep(@PathVariable cep: String): ResponseEntity<AddressRequest> {
        try {
            return addressService.getAddress(formatCep.formatCep(cep))
        }catch (addressNotFoundException: AddressNotFoundException){
            throw AddressNotFoundException(cep)
        }catch (cepLengthException: CepFormatException){
            throw CepFormatException(cep)
        }
    }

    @Operation(summary = "Cria um novo endereço")
    @PostMapping
    fun createAddress(@RequestBody addressRequest: AddressRequest): ResponseEntity<AddressRequest> {
       val newAddressRequest =  addressRequest.copy(cep = formatCep.formatCep(addressRequest.cep))
        try {
            return addressService.saveAddress(newAddressRequest)
        }catch (addressAlreadyExistsException: AddressAlreadyExistsException){
            throw AddressAlreadyExistsException(newAddressRequest.cep)
        }catch (cepLengthException: CepLengthException){
            throw CepLengthException(addressRequest.cep)
        }
    }

    @Operation(summary = "Atualiza um endereço")
    @PutMapping
    fun updateAddress(@RequestBody addressRequest: AddressRequest): ResponseEntity<AddressEntity> {
        //Substitui o cep recebido para um cep com apenas numeros
        val newAddressRequest =  addressRequest.copy(cep = formatCep.formatCep(addressRequest.cep))
        try {
            return addressService.updateAddress(newAddressRequest)
        }catch (addressNotFoundException: AddressNotFoundException){
            throw AddressNotFoundException(newAddressRequest.cep)
        }catch  (cepLengthException: CepLengthException){
            throw CepLengthException(addressRequest.cep)
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
