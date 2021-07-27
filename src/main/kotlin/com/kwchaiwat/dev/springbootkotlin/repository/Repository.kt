package com.kwchaiwat.dev.springbootkotlin.repository

import com.kwchaiwat.dev.springbootkotlin.model.Bank
import org.springframework.data.jpa.repository.JpaRepository

interface BankRepository: JpaRepository<Bank, Long>