package com.kwchaiwat.dev.springbootkotlin.datasource.mock

import com.kwchaiwat.dev.springbootkotlin.datasource.BankDataSource
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {

    private val mockDataSource : BankDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks` () {
        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        assertThat(banks).isNotEmpty
    }

    @Test
    fun `should provide some mock data`(){
        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        assertThat(banks).allMatch {
            it.accountNumber.isNotBlank()
            it.transactionFee != 0
        }
        assertThat(banks).anyMatch { it.trust != 0.0 }
    }
}
