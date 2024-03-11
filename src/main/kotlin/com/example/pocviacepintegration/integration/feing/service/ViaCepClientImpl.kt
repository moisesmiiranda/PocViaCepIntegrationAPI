package com.example.pocviacepintegration.integration.feing.service

import com.example.pocviacepintegration.integration.feing.client.ViaCepClient
import com.example.pocviacepintegration.integration.feing.response.AddressResponseViaCep
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ViaCepClientImpl (
    private val viaCepClient: ViaCepClient
) : ViaCepClient{
    override fun getCepFromViaCep(cep: String): ResponseEntity<AddressResponseViaCep> {
            return viaCepClient.getCepFromViaCep(cep)
        }
    }