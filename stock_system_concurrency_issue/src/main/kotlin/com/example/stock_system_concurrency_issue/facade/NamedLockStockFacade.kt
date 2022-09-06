package com.example.stock_system_concurrency_issue.facade

import com.example.stock_system_concurrency_issue.repository.LockRepository
import com.example.stock_system_concurrency_issue.service.StockService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class NamedLockStockFacade(
    private val lockRepository: LockRepository,
    private val stockService: StockService
) {

    @Transactional
    fun decrease(id: Long, quantity: Long) {
        try {
            lockRepository.getLock(id.toString())
            stockService.decrease(id, quantity)
        } finally {
            lockRepository.releaseLock(id.toString())
        }
    }
}