package com.example.bazaar_marketplace.repository

import com.example.bazaar_marketplace.api.RetrofitInstance
import com.example.bazaar_marketplace.models.LoginBody
import com.example.bazaar_marketplace.models.LoginResponse
import com.example.bazaar_marketplace.models.ProductsResponse
import retrofit2.Response

class Repository {
    suspend fun getProducts(token: String): Response<ProductsResponse> {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun login(loginBody: LoginBody): Response<LoginResponse> {
        return RetrofitInstance.api.login(loginBody)
    }
}