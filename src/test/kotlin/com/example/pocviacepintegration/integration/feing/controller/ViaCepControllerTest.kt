package com.example.pocviacepintegration.integration.feing.controller

import com.example.pocviacepintegration.integration.feing.client.ViaCepClient
import com.example.pocviacepintegration.integration.feing.response.AddressResponseViaCep
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.ResponseEntity
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WireMockTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = ["via-cep.url=https://localhost:8089"])
@AutoConfigureMockMvc
class ViaCepControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var viaCepClient: ViaCepClient

    @BeforeEach
    fun setUp(wireMockServer: WireMockServer) {
        wireMockServer.start()

        val addressResponseViaCep = AddressResponseViaCep(
            cep = "12345678",
            logradouro = "Rua Exemplo",
            complemento = "Complemento",
            bairro = "Bairro Exemplo",
            localidade = "Cidade Exemplo",
            uf = "UF",
            ibge = "1234567",
            gia = "GIA",
            ddd = "11",
            siafi = "1234"
        )

        `when`(viaCepClient.getCepFromViaCep("12345678"))
            .thenReturn(ResponseEntity.ok(addressResponseViaCep))

        `when`(viaCepClient.getCepFromViaCep("00000000"))
            .thenReturn(ResponseEntity.notFound().build())

        wireMockServer.stubFor(
            WireMock.get(WireMock.urlPathEqualTo("/ws/12345678/json"))
                .willReturn(
                    WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                            """
                            {
                                "cep": "12345678",
                                "logradouro": "Rua Exemplo",
                                "complemento": "Complemento",
                                "bairro": "Bairro Exemplo",
                                "localidade": "Cidade Exemplo",
                                "uf": "UF",
                                "ibge": "1234567",
                                "gia": "GIA",
                                "ddd": "11",
                                "siafi": "1234"
                            }
                            """.trimIndent()
                        )
                )
        )

        wireMockServer.stubFor(
            WireMock.get(WireMock.urlPathEqualTo("/ws/00000000/json"))
                .willReturn(WireMock.notFound())
        )
    }

    @Test
    fun `test get address by valid cep`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/viacep/12345678"))
            .andExpect(status().isOk)
    }

    @Test
    fun `test get address by invalid cep`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/viacep/00000000"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `test get address by invalid cep via service`() {

    }
}