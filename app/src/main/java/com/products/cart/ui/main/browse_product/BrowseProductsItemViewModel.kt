package com.products.cart.ui.main.browse_product

import android.view.View
import androidx.databinding.ObservableField
import com.products.cart.data.model.db.Product
import com.products.cart.ui.base.BaseCartItemListener

class BrowseProductsItemViewModel(
    private val product: Product,
    private val listener: BrowseProductsItemListener
) {
    val productName: ObservableField<String> = ObservableField<String>(product.name)
    val price = if (product.costPrice != null) product.costPrice else product.retailPrice
    val productPrice: ObservableField<String> =
        ObservableField(price.toString())

    fun onItemClick(view: View) {
        listener.onItemClick(view, product)
    }

    interface BrowseProductsItemListener : BaseCartItemListener<Product> {
    }

    fun onAddBtnClick() {
        listener.onAddClick(product)
    }
}