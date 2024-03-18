package com.example.pocviacepintegration.exceptions

data class AddressAlreadyExistsException(override val message: String?) : RuntimeException(message)