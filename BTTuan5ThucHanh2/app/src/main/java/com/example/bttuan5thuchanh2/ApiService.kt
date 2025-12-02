package com.example.bttuan5thuchanh2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("m1/890655-872447-default/v2/product")
    suspend fun getProduct(): Product
}

object RetrofitClient {
    private const val BASE_URL = "https://mock.apidog.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}