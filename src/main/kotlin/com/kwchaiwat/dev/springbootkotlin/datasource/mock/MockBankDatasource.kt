package com.kwchaiwat.dev.springbootkotlin.datasource.mock

import com.kwchaiwat.dev.springbootkotlin.datasource.BankDatasource
import com.kwchaiwat.dev.springbootkotlin.model.Bank
import org.springframework.stereotype.Repository

@Repository("mock")
class MockBankDatasource : BankDatasource{

    val banks = mutableListOf(
        Bank("32132", 3.14, 11),

        Bank("321", 0.33, 123),

        Bank("323114", 1.22, 232)
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank {
        val bank = banks.firstOrNull { it.accountNumber == accountNumber } ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
        return bank
    }

    override fun createBank(bank: Bank): Bank {
        if (banks.any{it.accountNumber == bank.accountNumber}){
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exits.")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number ${bank.accountNumber}")
        banks.remove(currentBank)
        banks.add(bank)
        return bank
    }

    override fun removeBank(accountNumber: String) {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
        banks.remove(currentBank)
    }
}