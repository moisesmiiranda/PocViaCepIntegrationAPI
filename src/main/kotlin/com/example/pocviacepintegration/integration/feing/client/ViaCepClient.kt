package com.example.pocviacepintegration.integration.feing.client

import com.example.pocviacepintegration.integration.feing.response.AddressResponseViaCep
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@FeignClient(name = "viaCepClient", url = "\$via-cep.url")
interface ViaCepClient {
    @GetMapping("/ws/{cep}/json")
    fun getCepFromViaCep(@PathVariable cep: String): ResponseEntity<AddressResponseViaCep>
}