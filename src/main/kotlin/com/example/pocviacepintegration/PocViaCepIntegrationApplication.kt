package com.example.pocviacepintegration

import com.example.pocviacepintegration.configuration.CepProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableFeignClients
@EnableKafka
class PocViaCepIntegrationApplication

fun main(args: Array<String>) {
	runApplication<PocViaCepIntegrationApplication>(*args)
}
