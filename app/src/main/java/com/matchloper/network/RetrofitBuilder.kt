package com.matchloper.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val baseUrl = "https://ec2-15-164-13-85.ap-northeast-2.compute.amazonaws.com"

    fun getRetrofit2() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val networkService : NetworkService = getRetrofit2().create(NetworkService::class.java)
}