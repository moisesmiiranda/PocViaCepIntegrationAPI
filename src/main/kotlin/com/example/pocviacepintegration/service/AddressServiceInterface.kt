package com.example.pocviacepintegration.service

import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.controller.response.AddressResponse
import com.example.pocviacepintegration.dto.AddressDTO
import com.example.pocviacepintegration.model.entities.AddressEntity
import org.springframework.http.ResponseEntity

interface AddressServiceInterface {

    fun getAddress(cep: String): ResponseEntity<AddressResponse>
    fun saveAddress(addressRequest: AddressRequest): ResponseEntity<AddressEntity>
    fun updateAddress(addressRequest: AddressRequest): ResponseEntity<AddressEntity>
    fun deleteAddress(cep: String): ResponseEntity<Unit>
}