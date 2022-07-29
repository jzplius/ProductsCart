package com.products.cart.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.products.cart.data.DataManager

abstract class BaseViewModel(
    private val dataManager: DataManager,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val isLoading = ObservableBoolean()

    fun getDataManager(): DataManager = dataManager

    fun getSaveStateHandle(): SavedStateHandle = savedStateHandle

    fun setIsLoading(b: Boolean) {
        isLoading.set(b)
    }
}