package com.example.bazaar_marketplace.viewModels.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar_marketplace.models.GeneralResponse
import com.example.bazaar_marketplace.models.RegisterBody
import com.example.bazaar_marketplace.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(private val repository: Repository) : ViewModel() {


    var registerResponse: MutableLiveData<Response<GeneralResponse>> = MutableLiveData()

    fun register(registerBody: RegisterBody) {
        viewModelScope.launch {
            val response = repository.register(registerBody)
            registerResponse.value = response
        }
    }

}