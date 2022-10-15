package com.example.hs_test.data.remote

import com.example.hs_test.data.*
import com.example.hs_test.data.model.ProductsPaged
import com.example.hs_test.data.model.ProductsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getProducts(): ProductsPaged? {
        return when (val response = fetchProducts()) {
            is Resource.Error -> null
            is Resource.Success -> {
                response.data?.data
            }
        }
    }

    private suspend fun fetchProducts(): Resource<ProductsResponse> = withContext(Dispatchers.IO) {
        try {
            handleSuccess(apiService.getProducts())
        } catch (e: Exception) {
            handleException(e)
        }
    }

}