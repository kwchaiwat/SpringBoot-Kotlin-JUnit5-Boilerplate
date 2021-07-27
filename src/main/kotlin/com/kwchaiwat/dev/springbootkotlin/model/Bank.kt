package com.kwchaiwat.dev.springbootkotlin.model

import java.io.Serializable
import javax.persistence.*

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

