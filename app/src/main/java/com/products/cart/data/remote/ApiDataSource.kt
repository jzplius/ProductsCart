package com.products.cart.data.remote

import com.products.cart.data.model.Result
import com.products.cart.data.model.api.ProductsResponse

interface ApiDataSource {
    suspend fun fetchProductsResponse(): Result<ProductsResponse>
}