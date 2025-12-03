package com.example.btnangcao2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {
    @GET("api/v3/ticker/24hr")
    suspend fun getTicker(@Query("symbol") symbol: String): BinanceTicker
}

object RetrofitClient {
    private const val BASE_URL = "https://api.binance.com/"

    val api: CryptoApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApi::class.java)
    }
}