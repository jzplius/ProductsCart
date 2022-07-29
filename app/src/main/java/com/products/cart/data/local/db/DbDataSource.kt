package com.products.cart.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.products.cart.data.model.db.Product

interface DbDataSource {
    fun getLiveDataProducts(): LiveData<MutableList<Product>>
    fun addProduct(product: Product)
    fun deleteProduct(id: Int)
    fun deleteAllProducts()

    //    fun checkProductExists(id: Int): MutableLiveData<Boolean>
    fun checkForFavoriteProduct(id: Int): Boolean
    fun getProductsCount(): Int
    fun getPricesSum(): MutableLiveData<String>
}