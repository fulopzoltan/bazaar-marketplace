package com.example.bazaar_marketplace.api

import com.example.bazaar_marketplace.models.*
import retrofit2.Response
import retrofit2.http.*

interface BazaarApi {
    @GET("/products")
    suspend fun getProducts(
        @Header("token") token: String,
        @Header("sort") sort: String = "{\"creation_time\" : 1}"
    ): Response<ProductsResponse>

    @GET("/products")
    suspend fun getProductsFiltered(
        @Header("token") token: String,
        @Header("filter") filter: String,
        @Header("sort") sort: String = "{\"creation_time\" : 1}"
    ): Response<ProductsResponse>

    @POST("/products/remove")
    suspend fun deleteProduct(
        @Header("token") token: String,
        @Query("product_id") productId: String
    ): Response<DeleteProductResponse>

    @POST("/user/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ): Response<LoginResponse>

    @POST("/user/register")
    suspend fun register(
        @Body registerBody: RegisterBody
    ): Response<GeneralResponse>


    @POST("/user/update")
    suspend fun updateUser(
        @Header("token") token: String,
        @Body profileUpdateBody: ProfileUpdateBody
    ): Response<ProfileUpdateResponse>

    @POST("/user/reset")
    suspend fun resetPassword(
        @Body resetPasswordBody: ResetPasswordBody
    ): Response<GeneralResponse>

}