package com.products.cart.ui.main.browse_product

import com.products.cart.ui.base.BaseEmptyItemListener

class EmptyItemViewModel(private val movieEmptyItemListener: MovieEmptyItemListener) {

    fun onRetryClick() {
        movieEmptyItemListener.onRetryClick()
    }

    interface MovieEmptyItemListener : BaseEmptyItemListener

}