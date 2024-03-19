package com.example.pocviacepintegration.controller

import com.example.pocviacepintegration.controller.mapper.AddressMapper
import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.controller.response.AddressResponse
import com.example.pocviacepintegration.exceptions.*
import com.example.pocviacepintegration.service.AddressServiceImpl
import com.example.pocviacepintegration.utils.FormatCep
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/address")
class AddressController @Autowired constructor(
    private val addressService: AddressServiceImpl,
    private val formatCep: FormatCep,
    private val addressMapper: AddressMapper
) {

    @Operation(summary = "Busca um endereço pelo CEP")
    @GetMapping("/{cep}")
    fun getAddressByCep(@PathVariable cep: String): ResponseEntity<AddressResponse> {
        if (formatCep.formatCep(cep).length != 8)
            throw CepLengthException(cep)
        try {
            val addressEntity = addressService.findByCep(formatCep.formatCep(cep))
            val addressResponse = addressMapper.toResponse(addressEntity)
            return ResponseEntity<AddressResponse>(addressResponse, HttpStatus.OK)
        }catch (addressNotFoundException: AddressNotFoundException){
            throw AddressNotFoundException(cep)
        }
    }

    @Operation(summary = "Cria um novo endereço")
    @PostMapping
    fun createAddress(@RequestBody @Valid addressRequest: AddressRequest): ResponseEntity<AddressResponse> {
            val cepFormated = formatCep.formatCep(addressRequest.cep)

           try {
               if (cepFormated.length != 8)
                   throw CepLengthException(addressRequest.cep)

               if (addressService.findById(cepFormated).isPresent)
                   throw AddressAlreadyExistsException(addressRequest.cep)

               val newAddressRequest =  addressRequest.copy(cep = formatCep.formatCep(addressRequest.cep))

               val addressToBD = addressMapper.requestToEntity(newAddressRequest)

               val addressResponse: AddressResponse = addressMapper.toResponse(addressService.save(addressToBD))

               return ResponseEntity.status(HttpStatus.CREATED).body(addressResponse)

           }catch (addressAlreadyExistsException: AddressAlreadyExistsException){
               throw AddressAlreadyExistsException(addressRequest.cep)
           }catch (cepLengthException: CepLengthException){
               throw CepLengthException(addressRequest.cep)
           }
    }

    @Operation(summary = "Atualiza um endereço")
    @PutMapping("/{cep}")
    fun updateAddress(
        @PathVariable(value = "cep") cep: String,
        @RequestBody @Valid addressRequest: AddressRequest):
            ResponseEntity<AddressResponse> {
        try {
            val addressResponse: AddressResponse = addressMapper.toResponse(addressService.findByCep(formatCep.formatCep(cep)))
            return ResponseEntity<AddressResponse>(addressResponse,HttpStatus.OK)
        }catch (addressNotFoundException: AddressNotFoundException){
            throw AddressNotFoundException(addressRequest.cep)
        }catch  (cepLengthException: CepLengthException){
            throw CepLengthException(addressRequest.cep)
        }
    }

    @Operation(summary = "Deleta um endereço")
    @DeleteMapping("/{cep}")
    fun deleteAddress(@PathVariable cep: String) : ResponseEntity<Unit>{
        try {
            val addressResponse = addressService.delete(formatCep.formatCep(cep))
            return ResponseEntity<Unit>(addressResponse,HttpStatus.NO_CONTENT)
        }catch (addressNotFoundException: AddressNotFoundException){
            throw AddressNotFoundException(cep)
        }
    }
}
