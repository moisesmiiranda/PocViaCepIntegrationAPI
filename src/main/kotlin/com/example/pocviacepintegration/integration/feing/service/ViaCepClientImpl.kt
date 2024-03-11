package com.example.pocviacepintegration.integration.feing.service

import com.example.pocviacepintegration.exceptions.AddressNotFoundException
import com.example.pocviacepintegration.integration.feing.client.ViaCepClient
import com.example.pocviacepintegration.integration.feing.response.AddressResponseViaCep
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ViaCepClientImpl (
    private val viaCepClient: ViaCepClient
) : ViaCepClient{
    override fun getCepFromViaCep(cep: String): ResponseEntity<AddressResponseViaCep> {
        val response = viaCepClient.getCepFromViaCep(cep)
        if (response.body?.cep == null){
            throw AddressNotFoundException(cep)
        } else{
            return viaCepClient.getCepFromViaCep(cep)
        }

        }
    }