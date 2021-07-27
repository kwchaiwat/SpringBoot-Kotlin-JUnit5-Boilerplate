package com.kwchaiwat.dev.springbootkotlin.service

import com.kwchaiwat.dev.springbootkotlin.repository.Repositories
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest{
    private val repository: Repositories = mockk(relaxed = true)
    private val bankService = BankService(repository)

    @Test
    fun `should call its data source to retrieve banks`() {
        // when
        bankService.getBanks()

        // Then
        verify(exactly = 1) { repository.retrieveBanks() }
    }
}