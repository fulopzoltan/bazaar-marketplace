package com.example.bazaar_marketplace.models

import com.google.gson.annotations.SerializedName

data class ProfileUpdateResponse(
    val code: String,
    val updatedData: UpdatedData,
    val timestamp: Long
)

data class UpdatedData(
    val username: String,
    @SerializedName("phone_number")
    val phoneNumber: Long,
    val email: String,
    val firebase_token: String,
    @SerializedName("is_activated")
    val isActivated: Boolean,
    @SerializedName("creation_time")
    val creationTime: Long,
    val token: String
)

data class ProfileUpdateBody(
    val username: String,
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: Long,
)
