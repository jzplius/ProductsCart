package com.products.cart.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.products.cart.data.model.db.Product
import org.koin.core.KoinComponent
import org.koin.core.inject

class DatabaseRepository : DbDataSource, KoinComponent {

    private val appDatabase: AppDatabase by inject()

    override fun getLiveDataProducts(): LiveData<MutableList<Product>> {
        return appDatabase.productsDAO.loadAll()
    }

    override fun addProduct(product: Product) {
        appDatabase.productsDAO.insert(product)
    }

    override fun deleteProduct(id: Int) {
        appDatabase.productsDAO.delete(id)
    }

    override fun deleteAllProducts() {
        appDatabase.productsDAO.deleteAll()
    }

    override fun checkForFavoriteProduct(id: Int): Boolean {
        return appDatabase.productsDAO.isTestExist(id) != 0
    }

    override fun getProductsCount(): Int {
        return appDatabase.productsDAO.countSum()
    }

    override fun getPricesSum(): MutableLiveData<String> {
        val sum: MutableLiveData<String> = MutableLiveData()
        sum.postValue(getProductsCount().toString())
        return sum
    }
}