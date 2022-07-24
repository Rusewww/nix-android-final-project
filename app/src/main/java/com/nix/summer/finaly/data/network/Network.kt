package com.nix.summer.finaly.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private const val API_KEY = "ccf1b649e067b3e344724149"

    private val client = OkHttpClient.Builder().build()

    val api: ExchangeServiceAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com/v6/$API_KEY/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(ExchangeServiceAPI::class.java)
    }
}