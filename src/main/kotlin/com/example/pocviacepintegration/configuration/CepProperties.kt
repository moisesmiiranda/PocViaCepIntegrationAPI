package com.example.pocviacepintegration.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CepProperties {
    @Value("\${VIA_CEP_URL:https://viacep.com.br}")
    lateinit var url: String
    @Value("\${CEP_LENGTH:8}")
    lateinit var cepLength: String
}