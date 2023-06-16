package com.example.cryptopricetracking.api

import com.example.cryptopricetracking.Models.ResponseCryptoModelItem
import retrofit2.Call
import retrofit2.http.GET

interface apiInterface {
    @GET("markets?vs_currency=usd")
    fun getdata(): Call<List<ResponseCryptoModelItem>>
}