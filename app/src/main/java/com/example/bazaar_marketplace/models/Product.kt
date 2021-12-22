package com.example.bazaar_marketplace.models

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("item_count")
    val itemCount: Int,
    val products: MutableList<Product>,
    val timestamp: Long
)

data class Product(
    val rating: Float,
    @SerializedName("amount_type")
    val amountType: String,
    @SerializedName("price_type")
    val priceType: String,
    @SerializedName("product_id")
    val productId: String,
    val username: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("price_per_unit")
    val pricePerUnit: String,
    val units: String,
    val description: String,
    val title: String,
    val images: List<String>,
    @SerializedName("creation_time")
    val dateCreated: Long,
    val messages: List<String>
)


data class DeleteProductResponse(
    val messages: String,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("deletion_time")
    val deletionTime: Long
)