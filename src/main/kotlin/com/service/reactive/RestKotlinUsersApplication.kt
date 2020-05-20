package com.service.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestKotlinUsersApplication

fun main(args: Array<String>) {
	runApplication<RestKotlinUsersApplication>(*args)
}
