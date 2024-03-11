package com.example.pocviacepintegration.exceptions

class CepLengthException (cep: String) : Exception("O tamanho do CEP tem que ser exatamente 8") {
}