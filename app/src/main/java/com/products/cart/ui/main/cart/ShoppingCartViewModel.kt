package com.products.cart.ui.main.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.products.cart.data.DataManager
import com.products.cart.data.model.db.Product
import com.products.cart.ui.base.BaseViewModel


class ShoppingCartViewModel(
    dataManager: DataManager, saveStateHandle: SavedStateHandle
) : BaseViewModel(dataManager, saveStateHandle) {

    val favoriteMoviesList: LiveData<MutableList<Product>> by lazy {
        getDataManager().getDatabaseRepository().getLiveDataProducts()
    }

    fun onBrowseClicked() {
        // Do nothing
    }

    fun onClearClicked() {
        getDataManager().getDatabaseRepository().deleteAllProducts()
        invalidateSum()
    }

    val pricesSum: MutableLiveData<String> by lazy {
        getDataManager().getDatabaseRepository().getPricesSum()
    }

    fun invalidateSum() {
        val sum = getDataManager().getDatabaseRepository().getProductsCount().toString()
        pricesSum.postValue(sum)
    }
}
