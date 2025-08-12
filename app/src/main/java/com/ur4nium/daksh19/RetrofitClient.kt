package com.ur4nium.daksh19


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://www.ipqualityscore.com/"

    // 1. Create a logging interceptor to see request and response details
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // 2. Create a custom OkHttpClient and add the interceptor to it
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // 3. Build Retrofit using this new custom client
    val apiService: IpqsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // <-- This line adds the logger
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IpqsApiService::class.java)
    }
}