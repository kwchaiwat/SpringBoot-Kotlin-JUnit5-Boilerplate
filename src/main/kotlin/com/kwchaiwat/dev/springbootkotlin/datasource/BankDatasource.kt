package com.kwchaiwat.dev.springbootkotlin.datasource

import com.kwchaiwat.dev.springbootkotlin.model.Bank

interface BankDatasource {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
    fun createBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
    fun removeBank(accountNumber: String)
}