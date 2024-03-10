package com.example.pocviacepintegration.dto

data class AddressDTO(
    val cep: String,
    val logradouro: String,
    val complemento: String?,
    val bairro: String,
    val localidade: String,
    val uf: String,
)
