package com.example.bazaar_marketplace.viewModels.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar_marketplace.models.GeneralResponse
import com.example.bazaar_marketplace.models.LoginBody
import com.example.bazaar_marketplace.models.LoginResponse
import com.example.bazaar_marketplace.models.ResetPasswordBody
import com.example.bazaar_marketplace.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val repository: Repository) : ViewModel() {

    var loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()
    val resetPasswordResponse: MutableLiveData<Response<GeneralResponse>> = MutableLiveData()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val response: Response<LoginResponse> = repository.login(LoginBody(username, password))
            loginResponse.value = response
        }
    }

    fun resetPassword(username: String, email: String) {
        viewModelScope.launch {
            val response = repository.resetPassword(ResetPasswordBody(username, email))
            resetPasswordResponse.value = response
        }
    }

}