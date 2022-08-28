package com.example.stock_system_concurrency_issue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockSystemConcurrencyIssueApplication

fun main(args: Array<String>) {
	runApplication<StockSystemConcurrencyIssueApplication>(*args)
}
