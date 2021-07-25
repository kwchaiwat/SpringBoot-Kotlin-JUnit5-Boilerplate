package com.kwchaiwat.dev.springbootkotlin.datasource.mock

import com.kwchaiwat.dev.springbootkotlin.datasource.BankDataSource
import com.kwchaiwat.dev.springbootkotlin.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource{
    val banks = listOf(
        Bank("abcdef", 3.14, 11),

        Bank("321", 0.33, 123),

        Bank("323114", 1.22, 232)
    )
    override fun retrieveBanks(): Collection<Bank> = banks
}