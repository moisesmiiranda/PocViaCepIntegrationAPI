package com.example.pocviacepintegration.service

import com.example.pocviacepintegration.dto.AddressDTO
import com.example.pocviacepintegration.model.entities.AddressEntity

interface AddressServiceInterface {

    fun getAddress(cep: String): AddressDTO
    fun saveAddress(addressDTO: AddressDTO): AddressEntity
    fun updateAddress(addressDTO: AddressDTO): AddressEntity
    fun deleteAddress(cep: String)
}