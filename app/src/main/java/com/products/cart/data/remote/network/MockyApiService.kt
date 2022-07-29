package com.products.cart.data.remote.network


import com.products.cart.data.model.api.ProductsResponse
import retrofit2.http.GET

interface MockyApiService {
    @GET("18532d04-e842-402e-ac43-10154fbc1f3e")
    suspend fun getProducts(): ProductsResponse
}