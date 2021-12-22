package com.example.bazaar_marketplace.api

import com.example.bazaar_marketplace.models.*
import retrofit2.Response
import retrofit2.http.*

interface BazaarApi {
    @GET("/products")
    suspend fun getProducts(
        @Header("token") token: String,
        @Header("sort") sort: String = "{\"creation_time\" : 1}",
        @Header("limit") limit: Int = 100
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

    @Multipart
    @POST("/products/add")
    suspend fun addProduct(
        @Header("token") token: String,
        @Part("title") title: String,
        @Part("description") description: String,
        @Part("price_per_unit") pricePerUnit: String,
        @Part("units") amount: String,
        @Part("is_active") isActive: Boolean,
        @Part("amount_type") amountType: String,
        @Part("price_type") currency: String,
        @Part("rating") rating: Double
    ): Response<AddProductResponse>

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