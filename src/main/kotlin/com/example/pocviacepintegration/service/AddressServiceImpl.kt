package com.example.pocviacepintegration.service

import com.example.pocviacepintegration.configuration.CepProperties
import com.example.pocviacepintegration.controller.mapper.AddressMapper
import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.controller.response.AddressResponse
import com.example.pocviacepintegration.exceptions.AddressAlreadyExistsException
import com.example.pocviacepintegration.exceptions.AddressNotFoundException
import com.example.pocviacepintegration.exceptions.CepLengthException
import com.example.pocviacepintegration.model.entities.AddressEntity
import com.example.pocviacepintegration.repository.AddressRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
    private val addressRepository: AddressRepository
) : AddressServiceInterface {
    override fun save(addressEntity: AddressEntity): AddressEntity {
        val addressExist = this.addressRepository.findById(addressEntity.cep).isPresent
        if (addressExist) throw AddressAlreadyExistsException(addressEntity.cep)
        return this.addressRepository.save(addressEntity)
    }

    override fun findByCep(cep: String): AddressEntity = this.addressRepository.findById(cep).orElseThrow { AddressNotFoundException(cep) }

    override fun delete(cep: String) {
        val address: AddressEntity = this.findByCep(cep)
        val addressExist = this.addressRepository.findById(address.cep).isPresent
        if (!addressExist) throw AddressNotFoundException(cep)
        this.addressRepository.delete(address)
    }
}