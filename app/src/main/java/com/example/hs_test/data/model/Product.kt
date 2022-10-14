package com.example.hs_test.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val img: String
)
