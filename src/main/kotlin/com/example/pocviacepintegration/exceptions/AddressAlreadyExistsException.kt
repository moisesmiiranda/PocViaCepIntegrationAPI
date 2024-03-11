package com.example.pocviacepintegration.exceptions

class AddressAlreadyExistsException(cep: String) : Exception("O cep $cep já existe na base de dados.")