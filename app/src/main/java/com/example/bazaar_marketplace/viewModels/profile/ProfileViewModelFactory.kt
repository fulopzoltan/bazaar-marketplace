package com.example.bazaar_marketplace.viewModels.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar_marketplace.repository.Repository

class ProfileViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}