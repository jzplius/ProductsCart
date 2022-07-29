package com.products.cart.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.products.cart.ui.base.BaseRecyclerViewAdapter

object BindingAdaptersUtils {

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:recyclerAdapter")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: MutableList<T>?) {
        items?.let { (recyclerView.adapter as BaseRecyclerViewAdapter<T>?)?.addItems(it) }
    }

}