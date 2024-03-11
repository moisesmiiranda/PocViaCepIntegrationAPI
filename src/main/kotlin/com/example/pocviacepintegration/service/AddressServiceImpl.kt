package com.example.pocviacepintegration.service

import com.example.pocviacepintegration.controller.mapper.AddressMapper
import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.controller.response.AddressResponse
import com.example.pocviacepintegration.dto.AddressDTO
import com.example.pocviacepintegration.exceptions.AddressAlreadyExistsException
import com.example.pocviacepintegration.exceptions.AddressNotFoundException
import com.example.pocviacepintegration.model.entities.AddressEntity
import com.example.pocviacepintegration.repository.AddressRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
    private val addressRepository: AddressRepository
) : AddressServiceInterface {
    override fun getAddress(cep: String): ResponseEntity<AddressResponse> {
        val addressEntity = addressRepository.findById(cep)

        return if (addressEntity.isPresent) {
            val entity = addressEntity.get()
            ResponseEntity.status(HttpStatus.OK).body(AddressMapper.entityToResponse(entity))
        } else {
            throw AddressNotFoundException(cep)
        }
    }

    override fun saveAddress(addressRequest: AddressRequest): ResponseEntity<AddressEntity> {
        val existingAddress = addressRepository.findById(addressRequest.cep)

        if (existingAddress.isPresent) {
            throw AddressAlreadyExistsException(addressRequest.cep)
        }else{
            val entity = AddressMapper.requestToEntity(addressRequest)
            val savedEntity = addressRepository.save(entity)
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity)
        }

    }

    override fun updateAddress(addressDTO: AddressDTO): AddressEntity {
        TODO("Not yet implemented")
    }

    override fun deleteAddress(cep: String) {
        TODO("Not yet implemented")
    }
}