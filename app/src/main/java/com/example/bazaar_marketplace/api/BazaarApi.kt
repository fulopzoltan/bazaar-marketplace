package com.example.bazaar_marketplace.api

import com.example.bazaar_marketplace.models.LoginBody
import com.example.bazaar_marketplace.models.LoginResponse
import com.example.bazaar_marketplace.models.ProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BazaarApi {
    @GET("/products")
    suspend fun getProducts(
        @Header("token") token: String,
        @Header("sort") sort: String = "{\"creation_time\" : 1}"
    ): Response<ProductsResponse>

    @POST("user/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ): Response<LoginResponse>
}