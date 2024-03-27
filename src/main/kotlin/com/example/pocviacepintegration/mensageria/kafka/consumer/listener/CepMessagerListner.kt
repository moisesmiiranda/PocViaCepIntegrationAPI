package com.example.pocviacepintegration.mensageria.kafka.consumer.listener

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CepMessagerListner {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
    @KafkaListener(topics = ["cep-messager"], groupId = "group_id")
    fun listen(message: String) {
        logger.info("Received Messager: $message")
    }
}