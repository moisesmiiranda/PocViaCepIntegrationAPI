package com.example.pocviacepintegration.controller

import com.example.pocviacepintegration.dto.AddressDTO
import com.example.pocviacepintegration.service.AddressServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AddressController @Autowired constructor(
    private val addressService: AddressServiceImpl
) {
    @GetMapping("/{cep}")
    fun getAddressByCep(@PathVariable cep: String): ResponseEntity<AddressDTO> {
        return ResponseEntity.ok(addressService.getAddress(cep))
    }
}
