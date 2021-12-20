package com.example.bazaar_marketplace.repository

import com.example.bazaar_marketplace.api.RetrofitInstance
import com.example.bazaar_marketplace.models.*
import retrofit2.Response

class Repository {
    suspend fun getProducts(token: String): Response<ProductsResponse> {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun login(loginBody: LoginBody): Response<LoginResponse> {
        return RetrofitInstance.api.login(loginBody)
    }

    suspend fun register(registerBody: RegisterBody):Response<GeneralResponse>{
        return RetrofitInstance.api.register(registerBody)
    }
}