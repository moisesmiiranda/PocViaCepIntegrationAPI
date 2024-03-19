package com.example.pocviacepintegration.unit.service

import com.example.pocviacepintegration.configuration.CepProperties
import com.example.pocviacepintegration.controller.mapper.AddressMapper
import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.model.entities.AddressEntity
import com.example.pocviacepintegration.repository.AddressRepository
import com.example.pocviacepintegration.service.AddressServiceImpl
import com.example.pocviacepintegration.service.AddressServiceInterface
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ExtendWith(MockKExtension::class)
@ActiveProfiles("test")
class AddressServiceImplTest {
    private lateinit var addressRepository: AddressRepository
    private lateinit var cepProperties: CepProperties
    private lateinit var addressService: AddressServiceInterface
    private lateinit var addressEntity: AddressEntity
    private lateinit var addressRequest: AddressRequest

    @BeforeEach
    fun setup() {
        addressRepository = mockk()
        cepProperties = mockk()
        addressRequest = mockk()
        addressService = AddressServiceImpl(addressRepository)

        addressEntity = AddressEntity(
            cep = "12345678",
            logradouro = "Rua Teste",
            complemento = "Complemento Teste",
            bairro = "Bairro Teste",
            localidade = "Cidade Teste",
            uf = "CE",
            ibge = "123321",
            gia = "9876",
            ddd = "88",
            siafi = "1212121212")
    }

    @Test
    fun `deve retornar endereço quando busca por CEP`() {
        // given
        val cep = "12345678"
        every { addressRepository.findById(cep) } returns Optional.of(addressEntity)
        every { cepProperties.cepLength } returns "8"

        // when
        val response = addressService.findByCep(cep)

        // then
        val expectedResponse = addressEntity

        assertEquals(expectedResponse, response)
    }


    @Test
    fun saveAddress() {
        // given
        every { addressRepository.save(addressEntity) } returns addressEntity
        every { cepProperties.cepLength } returns "8"


        // when
        val response = addressService.save(addressEntity)

        // then
        val expectedResponse = addressEntity

        assertEquals(expectedResponse, response)


        // then

        assertEquals(addressEntity, response)
    }

    @Test
    fun updateAddress() {
        // given
        val cep = "12345678"
        val updatedAddressEntity = AddressEntity(
            cep = cep,
            logradouro = "Rua Nova",
            complemento = "Novo Complemento",
            bairro = "Novo Bairro",
            localidade = "Nova Cidade",
            uf = "SP",
            ibge = "987654",
            gia = "54321",
            ddd = "11",
            siafi = "5555555"
        )

        every { addressRepository.findById(cep) } returns Optional.of(addressEntity)
        every { addressRepository.save(any()) } returns updatedAddressEntity
        every { cepProperties.cepLength } returns "8"

        // when
        val response = addressService.save(updatedAddressEntity)

        // then
        assertEquals(updatedAddressEntity, response)
    }

    @Test
    fun deleteAddress() {
        // given
        val cep = "12345678"

        every { addressRepository.findById(cep) } returns Optional.of(addressEntity)
        every { addressRepository.existsById(cep) } returns true
        every { cepProperties.cepLength } returns "8"
        every { addressRepository.delete(any()) } returns Unit

        // when
        assertDoesNotThrow { addressService.delete(cep) }

        // then
        verify { addressRepository.delete(addressEntity) }
    }
}