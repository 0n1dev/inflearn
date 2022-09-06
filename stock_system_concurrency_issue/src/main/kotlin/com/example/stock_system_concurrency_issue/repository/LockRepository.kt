package com.example.stock_system_concurrency_issue.repository

import com.example.stock_system_concurrency_issue.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LockRepository : JpaRepository<Stock, Long> {

    @Query(value = "SELECT get_lock(:key, 3000)", nativeQuery = true)
    fun getLock(key: String)

    @Query(value = "SELECT release_lock(:key)", nativeQuery = true)
    fun releaseLock(key: String)
}