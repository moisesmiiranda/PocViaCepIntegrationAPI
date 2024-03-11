package com.example.pocviacepintegration.controller

import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.controller.response.AddressResponse
import com.example.pocviacepintegration.exceptions.CepFormatException
import com.example.pocviacepintegration.model.entities.AddressEntity
import com.example.pocviacepintegration.service.AddressServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AddressController @Autowired constructor(
    private val addressService: AddressServiceImpl,
) {
    @GetMapping("/{cep}")
    fun getAddressByCep(@PathVariable cep: String): ResponseEntity<AddressResponse> {
        return addressService.getAddress(cep)
    }

    @PostMapping
    fun createAddress(@RequestBody addressRequest: AddressRequest): ResponseEntity<AddressEntity> {
       val newAddressRequest =  addressRequest.copy(cep = formatCep(addressRequest.cep))
        return addressService.saveAddress(newAddressRequest)
    }

    // Função utilitária para formatar o CEP removendo caracteres que não são numéricos
    private fun formatCep(cep: String): String {
        try {
            return cep.replace(Regex("[^0-9]"), "")
        } catch (cepFormatException: CepFormatException) {
            throw CepFormatException(cep)
        }

    }
}
