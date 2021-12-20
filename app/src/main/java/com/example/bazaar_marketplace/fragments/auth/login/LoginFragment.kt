package com.example.bazaar_marketplace.fragments.auth.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar_marketplace.R
import com.example.bazaar_marketplace.databinding.FragmentLoginBinding
import com.example.bazaar_marketplace.fragments.TimelineFragment
import com.example.bazaar_marketplace.fragments.auth.register.RegisterFragment
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.*
import com.example.bazaar_marketplace.viewModels.login.LoginViewModel
import com.example.bazaar_marketplace.viewModels.login.LoginViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireActivity().application.getSharedPreferences(
            Constants.SHARED_PREF_KEY,
            Context.MODE_PRIVATE
        )
        redirectIfLoggedIn()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Hide toolbar and Navbar
        requireActivity().findViewById<Toolbar>(R.id.toolbar).remove()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation).remove()

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(
            requireActivity(),
            LoginViewModelFactory(Repository())
        )[LoginViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logInButton.setOnClickListener { onLoginClick() }
        binding.signUpButton.setOnClickListener{
            Navigator.replaceFragment(RegisterFragment(),true)
        }

        binding.passwordBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.passwordBoxLayout.error = null
            } else {
                binding.passwordBoxLayout.error = "Please provide a password"
            }
        }
        binding.usernameBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.usernameBoxLayout.error = null
            } else {
                binding.usernameBoxLayout.error = "Please provide a username"
            }
        }
    }

    private fun onLoginClick() {
        var error = false;
        val username = binding.usernameBox.text.toString()
        val password = binding.passwordBox.text.toString()
        if (!isRequiredFieldAndNotEmpty(username)) {
            error = true;
            binding.usernameBoxLayout.error = "Please provide a username"
        }
        if (!isRequiredFieldAndNotEmpty(password)) {
            error = true;
            binding.passwordBoxLayout.error = "Please provide a password"
        }

        if (error) return;

        loginViewModel.loginResponse.observe(viewLifecycleOwner) { response ->

            if (response.isSuccessful) {
                val body = response.body()
                requireActivity().findViewById<Toolbar>(R.id.toolbar).show()
                requireActivity().longSnackbar(binding.root, "Login Successful")
                if (body != null) {
                    sharedPreferences.saveToken(body.token)
                    sharedPreferences.saveEmail(body.email)
                    sharedPreferences.savePhoneNum(body.phoneNumber)
                    sharedPreferences.saveTokenCreation(body.creationTime)
                    sharedPreferences.saveTokenRefresh(body.refreshTime)
                    sharedPreferences.saveUsername(body.username)
                }
                Navigator.replaceFragment(TimelineFragment())
            } else {
                requireActivity().longSnackbar(binding.root, "Invalid credentials")
            }
        }

        loginViewModel.login(username, password)
    }

    private fun redirectIfLoggedIn() {
        Log.d("SHARED", sharedPreferences.toString())
        val token = sharedPreferences.getToken()
        if (token != null && token.isNotEmpty()) {
            Navigator.replaceFragment(TimelineFragment())
        }
    }
}