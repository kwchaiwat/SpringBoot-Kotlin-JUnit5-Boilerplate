package com.kwchaiwat.dev.springbootkotlin.service

import com.kwchaiwat.dev.springbootkotlin.datasource.BankDatasource
import com.kwchaiwat.dev.springbootkotlin.model.Bank
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(@Qualifier("network") private val datasource: BankDatasource) {

    fun getBanks(): Collection<Bank> = datasource.retrieveBanks()

    fun getBank(accountNumber: String): Bank = datasource.retrieveBank(accountNumber)

    fun addBank(bank: Bank): Bank = datasource.createBank(bank)

    fun updateBank(bank: Bank): Bank = datasource.updateBank(bank)

    fun removeBank(accountNumber: String): Unit = datasource.removeBank(accountNumber)
}