package com.kwchaiwat.dev.springbootkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringboardsApplication

fun main(args: Array<String>) {
	runApplication<SpringboardsApplication>(*args)
}
