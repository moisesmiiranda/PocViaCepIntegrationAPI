package com.example.pocviacepintegration

import com.example.pocviacepintegration.configuration.CepProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class PocViaCepIntegrationApplication

fun main(args: Array<String>) {
	runApplication<PocViaCepIntegrationApplication>(*args)
}
