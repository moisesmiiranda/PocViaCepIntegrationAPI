package com.example.pocviacepintegration.mensageria.kafka.producer.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ProducerService(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${topic.cep-messages}") private var cepMessage: String
){
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun sendMessage(message: String) {
        logger.info("Mensagem -> $message")
        kafkaTemplate.send(cepMessage, message)
    }
}