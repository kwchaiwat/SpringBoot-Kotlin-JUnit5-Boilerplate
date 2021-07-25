package com.kwchaiwat.dev.springbootkotlin.controller

import com.kwchaiwat.dev.springbootkotlin.model.Bank
import com.kwchaiwat.dev.springbootkotlin.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/banks")
class BankController(private val service: BankService) {

    @GetMapping
    fun getBanks(): Collection<Bank> = service.getBanks()

}