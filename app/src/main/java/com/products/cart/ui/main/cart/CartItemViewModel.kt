package com.products.cart.ui.main.cart

import android.view.View
import androidx.databinding.ObservableField
import com.products.cart.data.model.db.Product
import com.products.cart.ui.base.BaseCartItemListener


class CartItemViewModel(
    private val product: Product,
    private val listener: CartItemClickListener
) {

    val productName: ObservableField<String> = ObservableField(product.name)
    val price = if (product.costPrice != null) product.costPrice else product.retailPrice
    val productPrice: ObservableField<String> =
        ObservableField(price.toString())

    fun onItemClick(view: View) {
        listener.onItemClick(view, product)
    }

    interface CartItemClickListener : BaseCartItemListener<Product> {
    }

}