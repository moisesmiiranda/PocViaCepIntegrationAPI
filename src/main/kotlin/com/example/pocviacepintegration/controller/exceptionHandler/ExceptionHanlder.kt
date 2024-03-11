package com.example.pocviacepintegration.controller.exceptionHandler

import com.example.pocviacepintegration.exceptions.AddressAlreadyExistsException
import com.example.pocviacepintegration.exceptions.AddressNotFoundException
import com.example.pocviacepintegration.exceptions.CepFormatException
import com.example.pocviacepintegration.exceptions.CepLengthException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@ControllerAdvice
class ExceptionHanlder {
    @ExceptionHandler(AddressNotFoundException::class)
    fun handleAdressNotFound(exception: AddressNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(CepFormatException::class)
    fun handleCepFormatException(exception: CepFormatException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(AddressAlreadyExistsException::class)
    fun handleAddressAlreadyExists(exception: AddressAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.message)
    }

    @ExceptionHandler(CepLengthException::class)
    fun handleCepLengthException(exception: CepLengthException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }
}