package com.kwchaiwat.dev.springbootkotlin.service

import com.kwchaiwat.dev.springbootkotlin.repository.Repositories
import com.kwchaiwat.dev.springbootkotlin.model.Bank
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(@Qualifier("bank") private val repository: Repositories) {

    fun getBanks(): Collection<Bank> = repository.retrieveBanks()

    fun getBank(accountNumber: String): Bank = repository.retrieveBank(accountNumber)

    fun addBank(bank: Bank): Bank = repository.createBank(bank)

    fun updateBank(bank: Bank): Bank = repository.updateBank(bank)

    fun removeBank(accountNumber: String): Unit = repository.removeBank(accountNumber)
}