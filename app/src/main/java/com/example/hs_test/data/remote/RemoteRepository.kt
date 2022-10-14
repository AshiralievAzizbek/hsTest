package com.example.hs_test.data.remote

import com.example.hs_test.data.*
import com.example.hs_test.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getProducts(): List<Product> {
        return when (val response = fetchProducts()) {
            is Resource.Error -> {
                listOf()
            }
            is Resource.Success -> {
                response.data ?: listOf()
            }
        }
    }

    private suspend fun fetchProducts(): Resource<List<Product>> = withContext(Dispatchers.IO) {
        try {
            handleSuccess(apiService.getData())
        } catch (e: Exception) {
            handleException(e)
        }
    }

}