package com.example.pocviacepintegration.service

import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.controller.response.AddressResponse
import com.example.pocviacepintegration.model.entities.AddressEntity
import org.springframework.http.ResponseEntity
import java.util.*

interface AddressServiceInterface {

    fun save(addressEntity: AddressEntity): AddressEntity
    fun findByCep(cep: String): AddressEntity
    fun findById(id: String): Optional<AddressEntity>
    fun delete(cep: String)
}