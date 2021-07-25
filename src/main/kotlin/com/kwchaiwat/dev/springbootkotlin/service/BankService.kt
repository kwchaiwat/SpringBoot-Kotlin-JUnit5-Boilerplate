package com.kwchaiwat.dev.springbootkotlin.service

import com.kwchaiwat.dev.springbootkotlin.datasource.BankDataSource
import com.kwchaiwat.dev.springbootkotlin.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> {
        return dataSource.retrieveBanks()
    }
}