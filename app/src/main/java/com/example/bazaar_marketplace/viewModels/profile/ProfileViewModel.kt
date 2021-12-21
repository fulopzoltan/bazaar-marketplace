package com.example.bazaar_marketplace.viewModels.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar_marketplace.models.ProfileUpdateBody
import com.example.bazaar_marketplace.models.ProfileUpdateResponse
import com.example.bazaar_marketplace.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel(private val repository: Repository) : ViewModel() {

    var profileUpdateResponse: MutableLiveData<Response<ProfileUpdateResponse>> = MutableLiveData()

    fun updateProfile(token: String, profileUpdateBody: ProfileUpdateBody) {
        viewModelScope.launch {
            val response = repository.updateUser(token, profileUpdateBody)
            profileUpdateResponse.value = response
        }
    }
}