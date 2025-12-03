package com.example.bttuan4btvnn

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskApiService {
    @GET("tasks")
    suspend fun getTasks(): BaseResponse<List<Task>>

    // Giả sử API detail trả về 1 object Task hoặc wrapper chứa Task
    // Dựa trên URL task/1, mình map vào BaseResponse<Task> cho đồng bộ
    @GET("task/{id}")
    suspend fun getTaskDetail(@Path("id") id: Int): BaseResponse<Task>

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): BaseResponse<Any?> // Hoặc Response<Unit> tùy server
}

object RetrofitClient {
    private const val BASE_URL = "https://amock.io/api/researchUTH/"

    val apiService: TaskApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApiService::class.java)
    }
}