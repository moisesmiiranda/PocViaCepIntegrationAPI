package com.example.pocviacepintegration.controller.response

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

data class AddressResponse(
    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    val cep: String,
    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    val logradouro: String,
    val complemento: String?,
    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    val bairro: String,
    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    val localidade: String,
    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    val uf: String
)
