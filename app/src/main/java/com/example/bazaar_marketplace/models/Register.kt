package com.example.bazaar_marketplace.models

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    val username: String,
    val password: String,
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: Long
)


data class GeneralResponse(
    val code: Int,
    val message: String,
    val timestamp: Long
)
