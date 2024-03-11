package com.example.pocviacepintegration.controller.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

data class AddressRequest(
    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    val cep: String,
    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    val logradouro:String,
    val complemento: String? = null,
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
    val uf: String,
    val ibge: String? = null,
    val gia: String? = null,
    val ddd: String? = null,
    val siafi: String? = null
)
