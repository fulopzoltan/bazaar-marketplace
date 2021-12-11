package com.example.bazaar_marketplace.api

import com.example.bazaar_marketplace.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BazaarApi by lazy {
        retrofit.create(BazaarApi::class.java)
    }
}