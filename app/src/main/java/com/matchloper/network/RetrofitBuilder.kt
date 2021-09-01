package com.matchloper.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val baseUrl = "http://ec2-15-164-13-85.ap-northeast-2.compute.amazonaws.com:8080"

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val networkService : NetworkService = getRetrofit().create(NetworkService::class.java)
}