package com.example.pocviacepintegration.model.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="tb_address")
data class AddressEntity(
    @Id
    @Column(nullable = false, unique = true) val cep: String,
    @Column(nullable = false) val logradouro: String,
    val complemento: String?,
    @Column(nullable = false) val bairro: String,
    @Column(nullable = false) val localidade: String,
    @Column(nullable = false) val uf: String,
    val ibge: String?,
    val gia: String?,
    val ddd: String?,
    val siafi: String?
)
