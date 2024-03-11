package com.example.pocviacepintegration.exceptions

class AddressNotFoundException(cep: String) : Exception("Não foi possível encontrar o endereço para o CEP $cep")