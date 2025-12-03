package com.example.btnangcao2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val _cryptoList = MutableStateFlow<List<CryptoCoin>>(emptyList())
    val cryptoList = _cryptoList.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        startRealtimeUpdates()
    }

    private fun startRealtimeUpdates() {
        viewModelScope.launch {
            // Vòng lặp vĩnh cửu chừng nào ViewModel còn sống
            while (isActive) {
                fetchDataConcurrently()
                // Yêu cầu: Sau 3s gọi lại
                delay(3000)
            }
        }
    }

    private suspend fun fetchDataConcurrently() {
        try {
            val api = RetrofitClient.api
            val startTime = System.currentTimeMillis()

            // Giả lập việc gọi 4-5 API khác nhau hoặc các cặp tiền khác nhau cùng lúc
            val deferredBTC = viewModelScope.async { api.getTicker("BTCUSDT") }
            val deferredETH = viewModelScope.async { api.getTicker("ETHUSDT") }
            val deferredLTC = viewModelScope.async { api.getTicker("LTCUSDT") }
            val deferredXMR = viewModelScope.async { api.getTicker("XMRUSDT") } // Monero
            val deferredBNB = viewModelScope.async { api.getTicker("BNBUSDT") }

            // Chờ tất cả API trả về kết quả cùng lúc
            val results = awaitAll(deferredBTC, deferredETH, deferredLTC, deferredXMR, deferredBNB)

            // Log thời gian xử lý để kiểm chứng tốc độ
            Log.d("CryptoApp", "Fetched all in: ${System.currentTimeMillis() - startTime}ms")

            // Chuyển đổi dữ liệu API thô sang Model chung của UI
            val uiData = listOf(
                mapToCoin(results[0], "Bitcoin", "https://cryptologos.cc/logos/bitcoin-btc-logo.png"),
                mapToCoin(results[1], "Ethereum", "https://cryptologos.cc/logos/ethereum-eth-logo.png"),
                mapToCoin(results[2], "Litecoin", "https://cryptologos.cc/logos/litecoin-ltc-logo.png"),
                mapToCoin(results[3], "Monero", "https://cryptologos.cc/logos/monero-xmr-logo.png"),
                mapToCoin(results[4], "Binance Coin", "https://cryptologos.cc/logos/bnb-bnb-logo.png")
            )

            _cryptoList.value = uiData
            _isLoading.value = false

        } catch (e: Exception) {
            e.printStackTrace()
            // Xử lý lỗi (ví dụ mất mạng)
        }
    }

    private fun mapToCoin(ticker: BinanceTicker, name: String, iconUrl: String): CryptoCoin {
        return CryptoCoin(
            id = ticker.symbol,
            symbol = ticker.symbol.replace("USDT", ""),
            name = name,
            price = ticker.lastPrice.toDoubleOrNull() ?: 0.0,
            percentChange = ticker.priceChangePercent.toDoubleOrNull() ?: 0.0,
            iconUrl = iconUrl
        )
    }
}