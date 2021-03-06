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

    suspend fun deleteProduct(token: String, productId: String): Response<DeleteProductResponse> {
        return RetrofitInstance.api.deleteProduct(token, productId)
    }

    suspend fun addProduct(
        token: String,
        productBody: AddProductBody
    ): Response<AddProductResponse> {
        return RetrofitInstance.api.addProduct(
            token,
            productBody.title,
            productBody.description,
            productBody.pricePerUnit,
            productBody.amount,
            productBody.isActive,
            productBody.amountType,
            productBody.currency,
            productBody.rating
        )
    }

    suspend fun updateProduct(
        token: String,
        productId: String,
        profileUpdateBody: ProductUpdateBody
    ): Response<GeneralResponse> {
        return RetrofitInstance.api.updateProduct(
            token,
            productId,
            profileUpdateBody.title,
            profileUpdateBody.amountType,
            profileUpdateBody.description,
            profileUpdateBody.pricePerUnit,
            profileUpdateBody.priceType,
            profileUpdateBody.units,
            profileUpdateBody.isActive,
            profileUpdateBody.rating
        )
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