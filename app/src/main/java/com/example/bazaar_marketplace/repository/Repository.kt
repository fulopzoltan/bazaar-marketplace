package com.example.bazaar_marketplace.repository

import com.example.bazaar_marketplace.api.RetrofitInstance
import com.example.bazaar_marketplace.models.LoginBody
import com.example.bazaar_marketplace.models.LoginResponse
import com.example.bazaar_marketplace.models.ProductsResponse

class Repository {
    suspend fun getProducts(token: String): ProductsResponse {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun login(loginBody: LoginBody): LoginResponse {
        return RetrofitInstance.api.login(loginBody)
    }
}