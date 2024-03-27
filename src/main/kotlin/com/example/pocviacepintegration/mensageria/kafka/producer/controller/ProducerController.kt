package com.example.pocviacepintegration.mensageria.kafka.producer.controller

import com.example.pocviacepintegration.mensageria.kafka.producer.service.ProducerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/kafka")
class ProducerController (
    private val producerService: ProducerService
) {

    @PostMapping
    fun sendMessage(@RequestBody cepMessage: String): ResponseEntity<String> {
        producerService.sendMessage(cepMessage)
        return ResponseEntity.ok().body("Mensagem enviada: $cepMessage")
    }
}