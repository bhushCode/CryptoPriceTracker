package com.example.cryptopricetracking.Models

data class Roi(
    val currency: String,
    val percentage: Double,
    val times: Double
)