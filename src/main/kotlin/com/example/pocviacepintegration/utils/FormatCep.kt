package com.example.pocviacepintegration.utils

import org.springframework.stereotype.Component

@Component
class FormatCep {
     fun formatCep(cep: String): String {
         return cep.replace(Regex("[^0-9]"), "")
    }
}