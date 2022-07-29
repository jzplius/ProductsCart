package com.products.cart.di.module

import androidx.lifecycle.SavedStateHandle
import com.products.cart.data.DataManager
import com.products.cart.ui.main.MainViewModel
import com.products.cart.ui.main.cart.ShoppingCartViewModel
import com.products.cart.ui.main.browse_product.BrowseProductsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { provideDataManager() }

    viewModel { (handle: SavedStateHandle) -> MainViewModel(get(), handle) }
    viewModel { (handle: SavedStateHandle) -> BrowseProductsViewModel(get(), handle) }
    viewModel { (handle: SavedStateHandle) -> ShoppingCartViewModel(get(), handle) }
}

private fun provideDataManager() = DataManager()
