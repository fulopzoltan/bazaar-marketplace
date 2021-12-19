package com.example.bazaar_marketplace.viewModels.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar_marketplace.models.LoginBody
import com.example.bazaar_marketplace.models.LoginResponse
import com.example.bazaar_marketplace.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {

    var loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    var loginResponseError : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)


    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response: LoginResponse = repository.login(LoginBody(username, password))
                loginResponse.value = response
                loginResponseError.value = false
            } catch (ex: Exception) {
                loginResponseError.value = true
                Log.d("LOGIN_EX", ex.toString())
            }
        }
    }

}