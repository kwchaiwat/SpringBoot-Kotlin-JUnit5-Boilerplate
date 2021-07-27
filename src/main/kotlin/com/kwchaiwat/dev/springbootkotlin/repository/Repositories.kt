package com.kwchaiwat.dev.springbootkotlin.repository

import com.kwchaiwat.dev.springbootkotlin.model.Bank

interface Repositories {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
    fun createBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
    fun removeBank(accountNumber: String)
}