package com.kwchaiwat.dev.springbootkotlin.datasource.mock

import com.kwchaiwat.dev.springbootkotlin.datasource.BankDatasource
import com.kwchaiwat.dev.springbootkotlin.repository.BankRepository
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDatasourceTest {
    private val bankRepository: BankRepository = mockk(relaxed = true)
    private val mockDatasource : BankDatasource = MockBankDatasource(bankRepository)

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
            it.accountNumber?.isNotBlank()
            it.transactionFee != 0
        }
        assertThat(banks).anyMatch { it.trust != 0.0 }
    }
}
