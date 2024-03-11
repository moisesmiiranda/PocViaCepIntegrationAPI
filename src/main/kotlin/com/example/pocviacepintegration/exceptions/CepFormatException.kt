package com.example.pocviacepintegration.exceptions

class CepFormatException(cep: String) : Exception("Não foi possível formatar o cep: $cep") {
}