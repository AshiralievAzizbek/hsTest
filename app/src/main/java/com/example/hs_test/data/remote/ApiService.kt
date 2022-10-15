package com.example.hs_test.data.remote

import com.example.hs_test.data.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/user/restaurant/product")
    suspend fun getProducts(@Query("id") id: String = "3"): ProductsResponse
}