package com.example.stock_system_concurrency_issue.service

import com.example.stock_system_concurrency_issue.repository.StockRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class StockService(
    private val stockRepository: StockRepository
) {

//    @Transactional
//    @Synchronized fun decrease(id: Long, quantity: Long) {
//        val stock = stockRepository.findByIdOrNull(id) ?: throw RuntimeException()
//
//        stock.decrease(quantity)
//
//        stockRepository.save(stock)
//    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun decrease(id: Long, quantity: Long) {
        val stock = stockRepository.findByIdOrNull(id) ?: throw RuntimeException()

        stock.decrease(quantity)

        stockRepository.save(stock)
    }
}