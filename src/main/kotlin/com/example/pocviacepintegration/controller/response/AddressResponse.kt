package com.example.pocviacepintegration.controller.response

data class AddressResponse(
    val cep: String,
    val logradouro: String,
    val complemento: String?,
    val bairro: String,
    val localidade: String,
    val uf: String
)
