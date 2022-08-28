package com.example.stock_system_concurrency_issue.service

import com.example.stock_system_concurrency_issue.domain.Stock
import com.example.stock_system_concurrency_issue.repository.StockRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
internal class StockServiceTest @Autowired constructor(
    private val stockService: StockService,
    private val stockRepository: StockRepository
) {

    @BeforeEach
    fun before() {
        val stock = Stock(1L, 100L)

        stockRepository.save(stock)
    }

    @AfterEach
    fun after() {
        stockRepository.deleteAll();
    }

    @Test
    fun `재고 감소 테스트`() {
        stockService.decrease(1L, 1L)

        val stock = stockRepository.findByIdOrNull(1L) ?: throw RuntimeException()

        assertEquals(99, stock.quantity)
    }

    @Test
    fun `동시에 100개의 요청`() {
        val threadCount = 100

        val executorService = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(threadCount)

        for (i in 0 until threadCount) {
            executorService.submit {
                try {
                    stockService.decrease(1L, 1L)
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        val stock = stockRepository.findByIdOrNull(1L) ?: throw  RuntimeException()

        assertEquals(0L, stock.quantity)
    }
}