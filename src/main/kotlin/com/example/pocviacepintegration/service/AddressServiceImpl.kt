package com.example.pocviacepintegration.service

import com.example.pocviacepintegration.dto.AddressDTO
import com.example.pocviacepintegration.mapper.MapperImpl
import com.example.pocviacepintegration.model.entities.AddressEntity
import com.example.pocviacepintegration.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
    private val addressRepository: AddressRepository,
    private val mapper: MapperImpl
) : AddressServiceInterface {
    override fun getAddress(cep: String): AddressDTO {
        if (!addressRepository.existsById(cep)) {
            throw Exception("Address not found")
        }else{
            return mapper.fromEntity(addressRepository.findById(cep).get())
        }
    }

    override fun saveAddress(addressDTO: AddressDTO): AddressEntity {
        TODO("Not yet implemented")
    }

    override fun updateAddress(addressDTO: AddressDTO): AddressEntity {
        TODO("Not yet implemented")
    }

    override fun deleteAddress(cep: String) {
        TODO("Not yet implemented")
    }
}