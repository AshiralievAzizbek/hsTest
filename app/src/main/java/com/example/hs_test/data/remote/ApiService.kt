package com.example.hs_test.data.remote

import com.example.hs_test.data.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("pizzas")
    suspend fun getData(): List<Product>
}