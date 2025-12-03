package com.example.btnangcao2

// Model chung để hiển thị lên UI
data class CryptoCoin(
    val id: String,
    val symbol: String,
    val name: String,
    val price: Double,
    val percentChange: Double,
    val iconUrl: String
)

// Model giả lập phản hồi từ API (Dùng Binance làm mẫu)
data class BinanceTicker(
    val symbol: String,
    val lastPrice: String,
    val priceChangePercent: String
)