package com.sean.ecom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages= ["com.sean"])
class EcomApplication

fun main(args: Array<String>) {
	runApplication<EcomApplication>(*args)
}
