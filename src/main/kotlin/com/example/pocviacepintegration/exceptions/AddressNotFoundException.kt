package com.example.pocviacepintegration.exceptions

data class AddressNotFoundException(override val message: String?) : RuntimeException(message)