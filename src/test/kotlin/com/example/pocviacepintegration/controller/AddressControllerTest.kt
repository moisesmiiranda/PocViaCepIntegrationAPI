package com.example.pocviacepintegration.controller

import com.example.pocviacepintegration.controller.mapper.AddressMapper
import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.model.entities.AddressEntity
import com.example.pocviacepintegration.repository.AddressRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class AddressControllerTest {

    @Autowired
    private lateinit var addressRepository: AddressRepository
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/v1/address"
    }

    @BeforeEach fun setup() = addressRepository.deleteAll()

    @AfterEach fun tearDown() = addressRepository.deleteAll()


    @Test
    fun `should create an address and return 201 status`() {
        //given
        val addressEntity: AddressEntity = buildAddressEntity()
        val valueAsString: String = objectMapper.writeValueAsString(addressEntity)
        //when
        mockMvc
            .perform(
                        MockMvcRequestBuilders
                        .post(URL)
                        .contentType(
                            MediaType
                                .APPLICATION_JSON
                        )
                        .content(
                            valueAsString
                        )
            )
            .andExpect(MockMvcResultMatchers.status().isCreated) //verifica o status
            //then
            //validação dos retornos do created
            .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value("01001000"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value("Praça da Sé"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.complemento").value("lado ímpar"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value("Sé"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.localidade").value("São Paulo"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.uf").value("SP"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.ibge").value("3550308"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.gia").value("1004"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.ddd").value("11"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.siafi").value("7107"))
            .andDo(MockMvcResultHandlers.print()) //exibe informações no console
    }


    @Test
    fun `should not created address with same cep and return 409 status`(){
        //given
        addressRepository.save(buildAddressEntity(cep = "01001000"))
        val addressEntity: AddressEntity = buildAddressEntity(cep = "01001000")
        val valueAsString: String = objectMapper.writeValueAsString(addressEntity)
        //when
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isConflict)
    }

    fun buildAddressEntity(
        cep: String = "01001-000",
        logradouro: String = "Praça da Sé",
        complemento: String = "lado ímpar",
        bairro: String = "Sé",
        localidade: String = "São Paulo",
        uf: String = "SP",
        ibge: String = "3550308",
        gia: String = "1004",
        ddd: String = "11",
        siafi: String = "7107"
    ) = AddressEntity(
        cep = cep,
        logradouro = logradouro,
        complemento = complemento,
        bairro = bairro,
        localidade = localidade,
        uf = uf,
        ibge = ibge,
        gia = gia,
        ddd = ddd,
        siafi = siafi
    )

}