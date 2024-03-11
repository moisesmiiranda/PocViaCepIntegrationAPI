package com.example.pocviacepintegration.unitTest

import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.model.entities.AddressEntity
import com.example.pocviacepintegration.repository.AddressRepository
import com.example.pocviacepintegration.service.AddressServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest
class AddressServiceImplTest {
    @InjectMocks
    private lateinit var addressService: AddressServiceImpl

    @Mock
    private lateinit var addressRepository: AddressRepository

    @Test
    fun `Ao recuperar o endereço deve retornar OK`() {
        val cep = "12345678"
        val addressEntity = AddressEntity(
            cep = cep,
            logradouro = "Rua Teste",
            bairro = "Bairro Teste",
            complemento = "Complemento Teste",
            localidade = "Cidade Teste",
            uf = "SP",
            ibge = "12345678",
            gia = "12345678",
            ddd = "12",
            siafi = "12"

        )
        `when`(addressRepository.findById(cep)).thenReturn(java.util.Optional.of(addressEntity))

        val response = addressService.getAddress(cep)

        assertEquals(HttpStatus.OK, response.statusCode)

    }

    @Test
    fun `getAddress should throw AddressNotFoundException when address is not found`() {
        val cep = "12345678"
        `when`(addressRepository.findById(cep)).thenReturn(java.util.Optional.empty())


    }

    @Test
    fun `Método saveAddress deve retornar CREATED quando o endereço for criado com sucesso`() {

        val addressRequest = AddressRequest(
            cep = "12345678",
            logradouro = "Rua Teste",
            bairro = "Bairro Teste",
            complemento = "Complemento Teste",
            localidade = "Cidade Teste",
            uf = "SP",
            ibge = "12345678",
            gia = "12345678",
            ddd = "12",
            siafi = "123123"
        )
        val addressEntity = AddressEntity(
            cep = addressRequest.cep,
            logradouro = addressRequest.logradouro,
            bairro = addressRequest.bairro,
            complemento = addressRequest.complemento,
            localidade = addressRequest.localidade,
            uf = addressRequest.uf,
            ibge = addressRequest.ibge,
            gia = addressRequest.gia,
            ddd = addressRequest.ddd,
            siafi = addressRequest.siafi
        )
        `when`(addressRepository.findById(addressRequest.cep)).thenReturn(java.util.Optional.empty())
        `when`(addressRepository.save(addressEntity)).thenReturn(addressEntity)

        val response = addressService.saveAddress(addressRequest)

        assertEquals(HttpStatus.CREATED, response.statusCode)

    }
}