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

    override fun retrieveBanks(): Collection<Bank> {
        return jdbcTemplate.query("SELECT * FROM banks", rowMapper)
    }

    override fun retrieveBank(accountNumber: String): Bank {
        TODO("Not yet implemented")
    }

    override fun createBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun updateBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun removeBank(accountNumber: String) {
        TODO("Not yet implemented")
    }

}