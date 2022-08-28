package com.example.stock_system_concurrency_issue.repository

import com.example.stock_system_concurrency_issue.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Long> {
}