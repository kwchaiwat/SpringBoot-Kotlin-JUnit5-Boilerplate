package com.kwchaiwat.dev.springbootkotlin.model

import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable
import javax.persistence.*

interface BankRepository: JpaRepository<Bank, Long> {
    /**
     * Finds a bank by accountNumber.
     *
     * @param accountNumber The accountNumber of the Bank to find.
     * @return The Bank with the given accountNumber.
     */
    fun findByAccountNumber(accountNumber: String?): Bank?
}

@Table(name = "banks")
@Entity
data class Bank (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null): Serializable {

    @Column(name = "account_number", nullable = false)
    var accountNumber: String? = null

    @Column(name = "trust", nullable = false)
    var trust: Double? = null

    @Column(name = "transaction_fee", nullable = false)
    var transactionFee: Int? = null

    override fun hashCode(): Int {
        return this::class.hashCode()
    }
}

