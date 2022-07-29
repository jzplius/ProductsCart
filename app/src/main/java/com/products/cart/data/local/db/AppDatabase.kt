package com.products.cart.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.products.cart.data.local.db.dao.ProductsDAO
import com.products.cart.data.model.db.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val productsDAO: ProductsDAO
}