package com.example.hs_test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hs_test.data.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ProductDao
}