package com.products.cart.ui.base

interface BaseCartItemListener<T> : BaseItemListener<T> {
    fun onAddClick(item: T)
}