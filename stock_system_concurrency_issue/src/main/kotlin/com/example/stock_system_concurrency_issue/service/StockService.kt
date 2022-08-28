package com.example.stock_system_concurrency_issue.service

import com.example.stock_system_concurrency_issue.domain.Stock
import com.example.stock_system_concurrency_issue.repository.StockRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StockService(
    private val stockRepository: StockRepository
) {

    fun decrease(id: Long, quantity: Long) {
        val stock = stockRepository.findByIdOrNull(id) ?: throw RuntimeException()

        stock.decrease(quantity)

        stockRepository.save(stock)
    }
}