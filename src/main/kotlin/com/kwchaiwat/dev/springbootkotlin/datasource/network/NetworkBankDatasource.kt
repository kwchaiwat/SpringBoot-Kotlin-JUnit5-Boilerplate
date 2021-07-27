package com.kwchaiwat.dev.springbootkotlin.datasource.network

import com.kwchaiwat.dev.springbootkotlin.datasource.BankDatasource
import com.kwchaiwat.dev.springbootkotlin.model.Bank
import com.kwchaiwat.dev.springbootkotlin.repository.BankRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("network")
class NetworkBankDatasource(
    @Autowired private val bankRepository: BankRepository
): BankDatasource {
    val banks: MutableList<Bank> = bankRepository.findAll()

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank {
        return banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
    }

    override fun createBank(bank: Bank): Bank {
        if (banks.any{it.accountNumber == bank.accountNumber}) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exits.")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number ${bank.accountNumber}")
        with(banks) {
            remove(currentBank)
            add(bank)
        }
        return bank
    }

    override fun removeBank(accountNumber: String) {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
        banks.remove(currentBank)
    }

}