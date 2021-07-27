package com.kwchaiwat.dev.springbootkotlin.repository.impl

import com.kwchaiwat.dev.springbootkotlin.model.BankRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class BankRepositoryImplTest {
    @Autowired private val bankRepository: BankRepository? = null

    @Test
    fun `should provide a collection of banks` () {
        // when
        val banks = bankRepository?.findAll()

        // then
        assertThat(banks).isNotEmpty
    }

    @Test
    fun `should provide some mock data`(){
        // when
        val banks = bankRepository?.findAll()

        // then
        assertThat(banks).allMatch {
            it.accountNumber?.isNotBlank()
            it.transactionFee != 0
        }
        assertThat(banks).anyMatch { it.trust != 0.0 }
    }
}
