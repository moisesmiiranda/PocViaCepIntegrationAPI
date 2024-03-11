package com.example.pocviacepintegration.utils

import com.example.pocviacepintegration.exceptions.CepFormatException
import org.springframework.stereotype.Component

@Component
class FormatCep {
     fun formatCep(cep: String): String {
        try {
            return cep.replace(Regex("[^0-9]"), "")
        } catch (cepFormatException: CepFormatException) {
            throw CepFormatException(cep)
        }
    }
}