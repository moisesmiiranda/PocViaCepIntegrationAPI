package com.example.pocviacepintegration.integration.feing.controller
import com.example.pocviacepintegration.exceptions.AddressNotFoundException
import com.example.pocviacepintegration.exceptions.CepLengthException
import com.example.pocviacepintegration.integration.feing.response.AddressResponseViaCep
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity

@SpringBootTest
@WireMockTest(httpPort = 8089)
class ViaCepControllerTest {

    @Autowired
    private lateinit var viaCepController: ViaCepController

    @Test
    fun `test getAddressFromViaCep with valid cep`() {
        val cep = "01001-000"
        val expectedResponse = AddressResponseViaCep(cep, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP", "3550308", "1004", "11", "7107")

        stubFor(get(urlEqualTo("/ws/$cep/json"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("""{
                    "cep": "$cep",
                    "logradouro": "logradouro",
                    "complemento": "complemento",
                    "bairro": "bairro",
                    "localidade": "localidade",
                    "uf": "uf",
                    "ibge": "ibge",
                    "gia": "gia",
                    "ddd": "ddd",
                    "siafi": "siafi"
                }""")
                .withStatus(200)))

        val response = viaCepController.getAddressFromViaCep(cep)

        assertEquals(ResponseEntity.ok(expectedResponse), response)
    }

    @Test
    fun `test getAddressFromViaCep with invalid cep`() {
        val cep = "123"

        assertThrows(CepLengthException::class.java) {
            viaCepController.getAddressFromViaCep(cep)
        }
    }

    @Test
    fun `test getAddressFromViaCep with non-existent cep`() {
        val cep = "00000000"

        stubFor(get(urlEqualTo("/ws/$cep/json"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("""{ "erro": true }""")
                .withStatus(200)))

        assertThrows(AddressNotFoundException::class.java) {
            viaCepController.getAddressFromViaCep(cep)
        }
    }
}