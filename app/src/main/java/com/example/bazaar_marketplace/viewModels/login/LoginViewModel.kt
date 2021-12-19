package com.example.bazaar_marketplace.viewModels.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar_marketplace.models.LoginResponse
import com.example.bazaar_marketplace.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel(val repository: Repository) : ViewModel() {

    val loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()


    fun login(username: String, password: String) {
        viewModelScope.launch {
            Log.d("LOGIN", "Trying to log in with $username $password")
        }
    }

}