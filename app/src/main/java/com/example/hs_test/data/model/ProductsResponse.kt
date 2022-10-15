package com.example.hs_test.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ProductsResponse(
    val ok: Boolean,
    val data: ProductsPaged
)

data class ProductsPaged(
    val pagination: Pagination,
    val products: List<Products>
)

data class Products(
    val id: Int,
    val title: String,
    val products: List<Product>
)

data class Pagination(
    val available_pages: Int,
    val current_page_number: Int,
    val current_page_size: Int,
    val total_items_count: Int,
    val total_pages_count: Int
)

@Entity
data class Product(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val photo: String?,
    val amount: Double?
)