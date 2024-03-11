package com.example.pocviacepintegration.exceptions

class AdressNotFound(cep: String) : Exception("Não foi possível encontrar o endereço para o CEP $cep")