package com.example.bazaar_marketplace.api

import com.example.bazaar_marketplace.models.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface BazaarApi {
    @GET("/products")
    suspend fun getProducts(
        @Header("token") token: String,
        @Header("sort") sort: String = "{\"creation_time\" : 1}"
    ): ProductsResponse
}