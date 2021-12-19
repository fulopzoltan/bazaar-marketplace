package com.example.bazaar_marketplace.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val username: String,
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: Int,
    val token: String,
    @SerializedName("creation_time")
    val creationTime: Long,
    @SerializedName("refresh_time")
    val refreshTime: Long,

    )