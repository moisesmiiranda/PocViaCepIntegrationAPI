package com.example.pocviacepintegration.unitTest


import com.example.pocviacepintegration.repository.AddressRepository
import com.example.pocviacepintegration.service.AddressServiceImpl
import org.hibernate.validator.internal.util.Contracts.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AddressServiceImplTest (
    @Mock
    private val addressRepository: AddressRepository,
    @InjectMocks
    private val addressServiceImpl: AddressServiceImpl
){
    @BeforeEach
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetAddressByCep(){
        val cep = "01001-000"
        val address = addressServiceImpl.getAddress(cep)
        assertNotNull(address)
    }


}