package com.example.cryptopricetracking

import com.example.cryptopricetracking.api.apiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofInstance {
  val retrofit by lazy {
      Retrofit.Builder().baseUrl("https://api.coingecko.com/api/v3/coins/")
          .addConverterFactory(GsonConverterFactory.create())
          .build()
  }
    val apiinterface by lazy {
        retrofit.create(apiInterface::class.java)
    }
}