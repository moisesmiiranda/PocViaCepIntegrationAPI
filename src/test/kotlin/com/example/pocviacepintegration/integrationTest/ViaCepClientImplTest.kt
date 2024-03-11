package com.example.pocviacepintegration.integrationTest

import com.example.pocviacepintegration.exceptions.AddressNotFoundException
import com.example.pocviacepintegration.integration.feing.client.ViaCepClient
import com.example.pocviacepintegration.integration.feing.response.AddressResponseViaCep
import com.example.pocviacepintegration.integration.feing.service.ViaCepClientImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ViaCepClientImplTest {
    @Test
    fun `test getCepFromViaCep success`() {
        val mockResponseEntity = ResponseEntity.ok().body(
            AddressResponseViaCep(
                cep = "01234567",
                logradouro = "Rua Teste",
                complemento = "Complemento Teste",
                bairro = "Bairro Teste",
                localidade = "Cidade Teste",
                uf = "SP",
                ibge = "123123",
                gia = "123123",
                ddd = "13",
                siafi = "123"
            )
        )
        val mockViaCepClient = mock(ViaCepClient::class.java)
        `when`(mockViaCepClient.getCepFromViaCep("01001000")).thenReturn(mockResponseEntity)

        val viaCepClient = ViaCepClientImpl(mockViaCepClient)
        val response = viaCepClient.getCepFromViaCep("01001000")

        assertEquals(HttpStatus.OK, response.statusCode)
        // Verifique se o objeto de resposta está correto conforme a resposta simulada
    }

    @Test
    fun `test getCepFromViaCep address not found`() {
        val mockViaCepClient = mock(ViaCepClient::class.java)
        `when`(mockViaCepClient.getCepFromViaCep("01001000")).thenThrow(AddressNotFoundException::class.java)

        val viaCepClient = ViaCepClientImpl(mockViaCepClient)

        // Verifique se a exceção AddressNotFoundException é lançada
        assertThrows<AddressNotFoundException> {
            viaCepClient.getCepFromViaCep("01001000")
        }
    }
}