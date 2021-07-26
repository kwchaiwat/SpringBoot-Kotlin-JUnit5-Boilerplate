package com.kwchaiwat.dev.springbootkotlin.datasource.network

import com.kwchaiwat.dev.springbootkotlin.datasource.BankDatasource
import com.kwchaiwat.dev.springbootkotlin.model.Bank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository("network")
class NetworkBankDatasource(
    @Autowired private val jdbcTemplate: JdbcTemplate
): BankDatasource {

    var rowMapper: RowMapper<Bank> = RowMapper<Bank> { resultSet: ResultSet, _: Int ->
        Bank(resultSet.getString("account_number"), resultSet.getDouble("trust"), resultSet.getInt("transaction_fee"))
    }

    val banks: MutableList<Bank> = jdbcTemplate.query("SELECT * FROM banks", rowMapper)

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
        jdbcTemplate.update("INSERT INTO banks (account_number, trust, transaction_fee) VALUES (?,?,?)", bank.accountNumber, bank.trust, bank.transactionFee)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number ${bank.accountNumber}")
        with(banks) {
            remove(currentBank)
            add(bank)
        }
        jdbcTemplate.update("UPDATE banks SET trust = ?, transaction_fee = ? WHERE account_number = ?", bank.trust, bank.transactionFee, bank.accountNumber)
        return bank
    }

    override fun removeBank(accountNumber: String) {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
        banks.remove(currentBank)
        jdbcTemplate.update("DELETE FROM banks WHERE account_number LIKE ?", accountNumber)
    }

}