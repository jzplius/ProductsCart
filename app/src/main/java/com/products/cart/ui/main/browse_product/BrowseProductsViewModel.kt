package com.products.cart.ui.main.browse_product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.products.cart.data.DataManager
import com.products.cart.data.model.Result
import com.products.cart.data.model.api.ProductsResponse
import com.products.cart.data.model.db.Product
import com.products.cart.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class BrowseProductsViewModel(dataManager: DataManager, saveStateHandle: SavedStateHandle) :
    BaseViewModel(dataManager, saveStateHandle) {

    private val browseProductsList: MutableLiveData<List<Product>> = MutableLiveData()

    init {
        fetchBrowseProductsList()
    }

    fun fetchBrowseProductsList() {
        viewModelScope.launch() {
            when (val result = getDataManager().getApiRepository().fetchProductsResponse()) {
                is Result.Success<ProductsResponse> -> {
                    browseProductsList.value = result.data.data
                }
                is Result.Error -> {
                    Log.i("Here", "SimilarMovies Failed")
                }
            }
        }
    }

    val browseProductsLiveData: LiveData<List<Product>> get() = browseProductsList

    fun onAddClick(product: Product) {
        getDataManager().getDatabaseRepository().addProduct(product)
    }
}