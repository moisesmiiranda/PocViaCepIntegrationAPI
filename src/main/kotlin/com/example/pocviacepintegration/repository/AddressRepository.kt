package com.example.pocviacepintegration.repository

import com.example.pocviacepintegration.model.entities.AddressEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AddressRepository : JpaRepository<AddressEntity, String> {
    fun findByCep(cep: String): AddressEntity?

    @Query(value = "SELECT * FROM tb_address WHERE localidade = ?", nativeQuery = true)
    fun findAllByCity(cidade: String): List<AddressEntity>
}