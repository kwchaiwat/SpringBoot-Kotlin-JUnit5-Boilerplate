package com.kwchaiwat.dev.springbootkotlin.datasource

import com.kwchaiwat.dev.springbootkotlin.model.Bank

interface BankDataSource {

    fun retrieveBanks(): Collection<Bank>
}