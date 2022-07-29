package com.products.cart.data.remote

import com.products.cart.data.model.Result
import com.products.cart.data.model.api.ProductsResponse
import com.products.cart.data.remote.network.MockyApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class ApiRepository : ApiDataSource, KoinComponent {

    private val apiService: MockyApiService by inject()

    override suspend fun fetchProductsResponse(): Result<ProductsResponse> {
        return try {
            val movieDetails = apiService.getProducts()
            Result.Success(movieDetails)
        } catch (exception: Exception) {
            Result.Error(exception.localizedMessage)
        }
    }
}