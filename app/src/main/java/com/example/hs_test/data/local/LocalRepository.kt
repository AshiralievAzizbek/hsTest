package com.example.hs_test.data.local

import com.example.hs_test.data.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepository @Inject constructor(private val productDao: ProductDao) {
    fun getProducts(): Flow<List<Product>> {
        return productDao.getProducts()
    }

    suspend fun insertProducts(products: List<Product>) {
        productDao.insertProducts(products)
    }

}