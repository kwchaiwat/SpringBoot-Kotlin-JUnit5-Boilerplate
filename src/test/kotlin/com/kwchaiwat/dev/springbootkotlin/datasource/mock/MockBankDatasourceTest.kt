package com.kwchaiwat.dev.springbootkotlin.datasource.mock

import com.kwchaiwat.dev.springbootkotlin.datasource.BankDatasource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDatasourceTest {

    private val mockDatasource : BankDatasource = MockBankDatasource()

    @Test
    fun `should provide a collection of banks` () {
        // when
        val banks = mockDatasource.retrieveBanks()

        // then
        assertThat(banks).isNotEmpty
    }

    @Test
    fun `should provide some mock data`(){
        // when
        val banks = mockDatasource.retrieveBanks()

        // then
        assertThat(banks).allMatch {
            it.accountNumber.isNotBlank()
            it.transactionFee != 0
        }
        assertThat(banks).anyMatch { it.trust != 0.0 }
    }
}
