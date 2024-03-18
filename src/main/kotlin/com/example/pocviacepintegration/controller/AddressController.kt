package com.example.pocviacepintegration.controller

import com.example.pocviacepintegration.controller.mapper.AddressMapper
import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.controller.response.AddressResponse
import com.example.pocviacepintegration.exceptions.AddressAlreadyExistsException
import com.example.pocviacepintegration.exceptions.AddressNotFoundException
import com.example.pocviacepintegration.exceptions.CepFormatException
import com.example.pocviacepintegration.exceptions.CepLengthException
import com.example.pocviacepintegration.model.entities.AddressEntity
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
        }catch (cepLengthException: CepFormatException){
            throw CepFormatException(cep)
        }
    }

    @Operation(summary = "Cria um novo endereço")
    @PostMapping
    fun createAddress(@RequestBody addressRequest: AddressRequest): ResponseEntity<AddressResponse> {


           try {
               if (formatCep.formatCep(addressRequest.cep).length != 8)
                   throw CepLengthException(addressRequest.cep)
               val existingAddress: AddressEntity = (addressService.findByCep(formatCep.formatCep(addressRequest.cep)))
               if(existingAddress == null)
               val newAddressRequest =  addressRequest.copy(cep = formatCep.formatCep(addressRequest.cep))

               val address = addressMapper.requestToEntity(newAddressRequest)

               val addressResponse: AddressResponse = addressMapper.toResponse(addressService.save(address))

               return ResponseEntity<AddressResponse>(addressResponse, HttpStatus.CREATED)

           }catch (addressAlreadyExistsException: AddressAlreadyExistsException){
               throw AddressAlreadyExistsException(newAddressRequest.cep)
           }catch (cepLengthException: CepLengthException){
               throw CepLengthException(addressRequest.cep)
           }
    }

    @Operation(summary = "Atualiza um endereço")
    @PutMapping
    fun updateAddress(
        @RequestParam(value = "cep") cep: String,
        @RequestBody @Valid addressRequest: AddressRequest):
            ResponseEntity<AddressResponse> {
        try {
            val address: AddressResponse = addressService.findByCep(formatCep.formatCep(cep))
            return ResponseEntity<AddressResponse>(HttpStatus.OK).body(addressResponse)
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
