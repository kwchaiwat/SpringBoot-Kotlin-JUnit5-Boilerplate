package com.kwchaiwat.dev.springbootkotlin.repository.impl

import com.kwchaiwat.dev.springbootkotlin.model.Bank
import com.kwchaiwat.dev.springbootkotlin.model.BankRepository
import com.kwchaiwat.dev.springbootkotlin.repository.Repositories
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("bank")
class BankRepositoryImpl(
    @Autowired private val bankRepository: BankRepository
): Repositories {

    override fun retrieveBanks(): Collection<Bank> = bankRepository.findAll()

    override fun retrieveBank(accountNumber: String): Bank {
        return bankRepository.findByAccountNumber(accountNumber)
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
    }

    override fun createBank(bank: Bank): Bank {
        if (bankRepository.findAll().any{it.accountNumber == bank.accountNumber}) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exits.")
        }
        bankRepository.save(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = bankRepository.findAll().firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number ${bank.accountNumber}")
        bankRepository.delete(currentBank)
        bankRepository.save(bank)
        return bank
    }

    override fun removeBank(accountNumber: String) {
        val banks: Bank = bankRepository.findByAccountNumber(accountNumber)
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
        bankRepository.delete(banks)
    }

}