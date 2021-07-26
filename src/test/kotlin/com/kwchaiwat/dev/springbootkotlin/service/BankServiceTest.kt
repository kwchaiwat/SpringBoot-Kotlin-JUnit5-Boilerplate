package com.kwchaiwat.dev.springbootkotlin.service

import com.kwchaiwat.dev.springbootkotlin.datasource.BankDatasource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest{
    private val datasource: BankDatasource = mockk(relaxed = true)
    private val bankService = BankService(datasource)

    @Test
    fun `should call its data source to retrieve banks`() {
        // when
        bankService.getBanks()

        // Then
        verify(exactly = 1) { datasource.retrieveBanks() }
    }
}