package com.example.pocviacepintegration.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="tb_address")
data class AddressEntity(
    @Id
    val cep: String,
    val logradouro: String,
    val complemento: String?,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val ibge: String?,
    val gia: String?,
    val ddd: String?,
    val siafi: String?
)
