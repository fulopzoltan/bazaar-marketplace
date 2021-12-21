package com.example.bazaar_marketplace.api

import com.example.bazaar_marketplace.models.*
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.*

interface BazaarApi {
    @GET("/products")
    suspend fun getProducts(
        @Header("token") token: String,
        @Header("sort") sort: String = "{\"creation_time\" : 1}"
    ): Response<ProductsResponse>

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

//    @Multipart
//    @POST("/user/update")
//    suspend fun updateUser(
//        @Header("token") token: String,
//        @Part("username") username: String,
//        @Part("email") email: String,
//        @Part("phone_number") phoneNumber: String,
//    ): Response<ProfileUpdateResponse>
}


//data class ProfileUpdateBody(
//    val username: String,
//    val email: String,
//    @SerializedName("phone_number")
//    val phoneNumber: Long,
//)


//data class ProfileUpdateResponse(
//    val code: String,
//    val updatedData: UpdatedData,
//    val timestamp: Long
//)
//
//data class UpdatedData(
//    val username: String,
//    @SerializedName("phone_number")
//    val phoneNumber: Long,
//    val email: String,
//    val firebase_token: String,
//    @SerializedName("is_activated")
//    val isActivated: Boolean,
//    @SerializedName("creation_time")
//    val creationTime: Long,
//    val token: String
//)