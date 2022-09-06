package com.example.stock_system_concurrency_issue.facade

import com.example.stock_system_concurrency_issue.service.OptimisticLockStockService
import org.springframework.stereotype.Service

@Service
class OptimisticLockStockFacade(
    private val stockService: OptimisticLockStockService
) {

    fun decrease(id: Long, quantity: Long) {
        while (true) {
            try {
                stockService.decrease(id, quantity)
                break
            } catch (e: Exception) {
                Thread.sleep(50)
            }
        }
    }
}