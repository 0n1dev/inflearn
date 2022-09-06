package com.example.stock_system_concurrency_issue.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Version

@Entity
class Stock(
    val productId: Long,
    var quantity: Long,

    @Version
    var version: Long? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

    fun decrease(quantity: Long) {
        if (this.quantity - quantity < 0) {
            throw RuntimeException("quantity")
        }

        this.quantity -= quantity
    }
}