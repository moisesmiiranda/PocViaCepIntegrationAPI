package com.example.pocviacepintegration.integration.feing.controller

import com.example.pocviacepintegration.configuration.CepProperties
import com.example.pocviacepintegration.exceptions.AddressNotFoundException
import com.example.pocviacepintegration.exceptions.CepLengthException
import com.example.pocviacepintegration.integration.feing.client.ViaCepClient
import com.example.pocviacepintegration.integration.feing.response.AddressResponseViaCep
import com.example.pocviacepintegration.utils.FormatCep
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/viacep")
class ViaCepController @Autowired constructor(
    private val viaCepService: ViaCepClient,
    private val cepProperties: CepProperties,
    private val formatCep: FormatCep
) {
    @GetMapping("/{cep}")
    fun getAddressFromViaCep(@PathVariable cep: String): ResponseEntity<AddressResponseViaCep> {
        if (formatCep.formatCep(cep).length != cepProperties.cepLength.toInt()){
            throw CepLengthException(cep)
        }

        val response = viaCepService.getCepFromViaCep(cep)

        if (response.body?.cep == null) {
            throw AddressNotFoundException(cep)
        }else{
            return ResponseEntity(response.body, response.statusCode)
        }

    }
}
