package com.products.cart.data

import com.products.cart.data.local.db.DatabaseRepository
import com.products.cart.data.remote.ApiRepository

class DataManager {
    private val apiRepository = ApiRepository()
    private val databaseRepository = DatabaseRepository()

    fun getApiRepository(): ApiRepository {
        return apiRepository
    }

    fun getDatabaseRepository(): DatabaseRepository {
        return databaseRepository
    }
}