package com.kwchaiwat.dev.springbootkotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloWorldController {

    @GetMapping("/springboot")
    fun helloWorld(): String = "Hello, This is a REST endpoint!"

}