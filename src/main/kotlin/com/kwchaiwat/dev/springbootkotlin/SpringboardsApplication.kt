package com.kwchaiwat.dev.springbootkotlin

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.client.RestTemplate

@Configuration
@EnableJpaAuditing
@SpringBootApplication
class SpringboardsApplication : CommandLineRunner{
	@Bean
	fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

	override fun run(vararg args: String?) {
		// IF YOU WANT TO DO SOMETHING WHEN RUN THIS APP
	}
}

fun main(args: Array<String>) {
	runApplication<SpringboardsApplication>(*args)
}
