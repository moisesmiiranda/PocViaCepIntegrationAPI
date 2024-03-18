package com.example.pocviacepintegration.controller.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

data class AddressRequest(
    @field:NotNull("Cep não pode ser nulo")
    @field:NotEmpty(message = "Cep não pode ser nulo")
    @field:NotBlank(message = "O campo CEP não pode estar em branco")
    val cep: String,
    @field:NotNull("O campo Logradouro não pode ser nulo")
    @field:NotEmpty(message = "O campo Logradouro não pode estar vazio")
    @field:NotBlank(message = "O campo Logradouro não pode estar em branco")
    val logradouro:String,
    val complemento: String? = null,
    @field:NotNull("O campo Bairro não pode ser nulo")
    @field:NotEmpty(message = "O campo Bairro não pode estar vazio")
    @field:NotBlank(message = "O campo Bairro não pode estar em branco")
    val bairro: String,
    @field:NotNull("O campo Localidade não pode ser nulo")
    @field:NotEmpty(message = "O campo Localidade não pode estar vazio")
    @field:NotBlank(message = "O campo Localidade não pode estar em branco")
    val localidade: String,
    @field:NotNull("O campo UF não pode ser nulo")
    @field:NotEmpty(message = "O campo UF não pode estar vazio")
    @field:NotBlank(message = "O campo UF não pode estar em branco")
    val uf: String,
    val ibge: String? = null,
    val gia: String? = null,
    val ddd: String? = null,
    val siafi: String? = null
)
