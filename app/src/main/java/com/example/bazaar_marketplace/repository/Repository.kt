package com.example.bazaar_marketplace.repository

import com.example.bazaar_marketplace.api.RetrofitInstance
import com.example.bazaar_marketplace.models.*
import retrofit2.Response

class Repository {
    suspend fun getProducts(token: String): Response<ProductsResponse> {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun getProductsFiltered(token: String, filter: String): Response<ProductsResponse> {
        return RetrofitInstance.api.getProductsFiltered(token, filter)
    }

    suspend fun login(loginBody: LoginBody): Response<LoginResponse> {
        return RetrofitInstance.api.login(loginBody)
    }

    suspend fun register(registerBody: RegisterBody): Response<GeneralResponse> {
        return RetrofitInstance.api.register(registerBody)
    }

    suspend fun updateUser(
        token: String,
        profileUpdateBody: ProfileUpdateBody
    ): Response<ProfileUpdateResponse> {
        return RetrofitInstance.api.updateUser(token, profileUpdateBody)
    }

    suspend fun resetPassword(resetPasswordBody: ResetPasswordBody): Response<GeneralResponse> {
        return RetrofitInstance.api.resetPassword(resetPasswordBody)
    }
}