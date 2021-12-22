package com.example.bazaar_marketplace.viewModels.product

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar_marketplace.models.DeleteProductResponse
import com.example.bazaar_marketplace.models.Product
import com.example.bazaar_marketplace.models.ProductsResponse
import com.example.bazaar_marketplace.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductViewModel(private val repository: Repository) : ViewModel() {

    val products: MutableLiveData<Response<ProductsResponse>> = MutableLiveData()
    val myProducts: MutableLiveData<Response<ProductsResponse>> = MutableLiveData()
    val deleteResponse: MutableLiveData<Response<DeleteProductResponse>> = MutableLiveData()

    fun getProducts(token: String) {
        viewModelScope.launch {
            val response: Response<ProductsResponse> = repository.getProducts(token)
            products.value = response
        }
    }

    fun getMyProducts(token: String, username: String) {
        viewModelScope.launch {
            val response: Response<ProductsResponse> =
                repository.getProductsFiltered(token, "{ \"username\": \"$username\" }")
            myProducts.value = response
        }
    }

    fun deleteProduct(token: String, productId: String, pos: Int) {
        viewModelScope.launch {
            val response = repository.deleteProduct(token, productId)
            deleteResponse.value = response
        }
    }

    fun selectForDetail(product: Product) {

    }

}