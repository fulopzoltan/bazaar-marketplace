package com.example.bazaar_marketplace.viewModels.product

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar_marketplace.models.ProductsResponse
import com.example.bazaar_marketplace.repository.Repository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: Repository) : ViewModel() {

    val products: MutableLiveData<ProductsResponse> = MutableLiveData()

    fun getProducts(token: String) {
        viewModelScope.launch {
            try {
                val response: ProductsResponse =
                    repository.getProducts(token)
                products.value = response
            } catch (ex: Exception) {
                Log.e("API_EXCEPTION", ex.toString())
            }

        }
    }
}