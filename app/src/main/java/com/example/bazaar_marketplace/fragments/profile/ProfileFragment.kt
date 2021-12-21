package com.example.bazaar_marketplace.fragments.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar_marketplace.databinding.FragmentProfileBinding
import com.example.bazaar_marketplace.fragments.auth.login.LoginFragment
import com.example.bazaar_marketplace.models.ProfileUpdateBody
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.*
import com.example.bazaar_marketplace.viewModels.profile.ProfileViewModel
import com.example.bazaar_marketplace.viewModels.profile.ProfileViewModelFactory

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireActivity().application.getSharedPreferences(
            Constants.SHARED_PREF_KEY,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(
            requireActivity(),
            ProfileViewModelFactory(Repository())
        )[ProfileViewModel::class.java]

        binding.logOutButton.setOnClickListener {
            sharedPreferences.clearAllData()
            Navigator.replaceFragment(LoginFragment())
        }
        binding.userName.text = sharedPreferences.getUsername()
        binding.publishButton.setOnClickListener { onPublish() }
        binding.emailBox.text =
            Editable.Factory.getInstance().newEditable(sharedPreferences.getEmail())
        binding.usernameBox.text =
            Editable.Factory.getInstance().newEditable(sharedPreferences.getUsername())
        binding.phoneNumBox.text =
            Editable.Factory.getInstance().newEditable(sharedPreferences.getPhoneNum().toString())
        setUpChangeEvents()
        return binding.root
    }

    private fun onPublish() {
        var error = false
        val username = binding.usernameBox.text.toString()
        val email = binding.emailBox.text.toString()
        val phoneNum = binding.phoneNumBox.text.toString()
        if (!isRequiredFieldAndNotEmpty(username)) {
            error = true;
            binding.usernameBoxLayout.error = "Please provide a username"
        }
        if (!isValidEmail(email)) {
            error = true;
            binding.emailBoxLayout.error = "Please provide a valid email!"
        }
        if (!isRequiredFieldAndNotEmpty(phoneNum)) {
            error = true;
            binding.phoneNumBoxLayout.error = "Please provide a phone number"
        }

        if (error) return;

        val token = sharedPreferences.getToken()

        profileViewModel.profileUpdateResponse.observe(viewLifecycleOwner) { response ->


            if (response.isSuccessful) {
                val body = response.body()
                body?.let { it ->
                    sharedPreferences.saveToken(it.updatedData.token)
                    sharedPreferences.saveEmail(it.updatedData.email)
                    sharedPreferences.savePhoneNum(it.updatedData.phoneNumber)
                    sharedPreferences.saveTokenCreation(it.updatedData.creationTime)
                    sharedPreferences.saveUsername(it.updatedData.username)
                }

                requireActivity().longSnackbar(binding.root, response.message())
            } else {
                requireActivity().longSnackbar(binding.root, response.message())
            }

        }

        profileViewModel.updateProfile(
            token!!,
            ProfileUpdateBody(username, email, phoneNum.toLong())
        )
    }

    private fun setUpChangeEvents() {
        binding.usernameBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.usernameBoxLayout.error = null
            } else {
                binding.usernameBoxLayout.error = "Please provide a username"
            }
        }
        binding.emailBox.doOnTextChanged { inputText, _, _, _ ->
            if (isValidEmail(inputText.toString())) {
                binding.emailBoxLayout.error = null
            } else {
                binding.emailBoxLayout.error = "Please provide a valid email!"
            }
        }
        binding.phoneNumBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.phoneNumBoxLayout.error = null
            } else {
                binding.phoneNumBoxLayout.error = "Please provide a phone number"
            }
        }
    }
}