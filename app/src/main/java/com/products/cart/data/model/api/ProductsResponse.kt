package com.products.cart.data.model.api

import com.google.gson.annotations.SerializedName
import com.products.cart.data.model.db.Product


data class ProductsResponse(
    @SerializedName("data")
    val `data`: List<Product>
)

