package com.example.bazaar_marketplace.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar_marketplace.models.ProductsResponse
import com.example.bazaar_marketplace.repository.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductViewModel(private val repository: Repository) : ViewModel() {

    val products: MutableLiveData<ProductsResponse> = MutableLiveData()

    fun getProducts(){
        viewModelScope.launch {
            try {
                val response: ProductsResponse = repository.getProducts("d3588c10d893f696e277888eebce4882374bfb10bdddc965de3c976e4c4f333b30a7ee6163c1f5e15944a58c130466ea48810bd82f22cd9a5cecf75b79b94cd8e611b7393fa82b47391f7869c4d04469")
                products.value = response
            }catch (ex: Exception){
                Log.e("API_EXCEPTION",ex.toString())
            }

        }
    }
}