package com.example.bazaar_marketplace.fragments.auth.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar_marketplace.databinding.FragmentRegisterBinding
import com.example.bazaar_marketplace.fragments.auth.login.LoginFragment
import com.example.bazaar_marketplace.models.RegisterBody
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.Navigator
import com.example.bazaar_marketplace.utils.isRequiredFieldAndNotEmpty
import com.example.bazaar_marketplace.utils.longSnackbar
import com.example.bazaar_marketplace.viewModels.register.RegisterViewModel
import com.example.bazaar_marketplace.viewModels.register.RegisterViewModelFactory

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //init binding
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        registerViewModel = ViewModelProvider(
            requireActivity(),
            RegisterViewModelFactory(Repository())
        )[RegisterViewModel::class.java]

        binding.registerButton.setOnClickListener { onRegisterClicked() }
        binding.logIn.setOnClickListener { Navigator.replaceFragment(LoginFragment(), true) }
        setUpChangeEvents()

        return binding.root
    }

    private fun onRegisterClicked() {
        var error = false
        val username = binding.usernameBox.text.toString()
        val email = binding.emailBox.text.toString()
        val phoneNum = binding.phoneNumBox.text.toString()
        val password = binding.passwordBox.text.toString()
        if (!isRequiredFieldAndNotEmpty(username)) {
            error = true;
            binding.usernameBoxLayout.error = "Please provide a username"
        }
        if (!isRequiredFieldAndNotEmpty(password)) {
            error = true;
            binding.passwordBoxLayout.error = "Please provide a password"
        }
        if (!isRequiredFieldAndNotEmpty(email)) {
            error = true;
            binding.emailBoxLayout.error = "Please provide an email"
        }
        if (!isRequiredFieldAndNotEmpty(phoneNum)) {
            error = true;
            binding.phoneNumBoxLayout.error = "Please provide a phone number"
        }

        if (error) return;

        registerViewModel.registerResponse.observe(viewLifecycleOwner) { response ->

            val message = response.message()
            if (response.isSuccessful) {
                requireActivity().longSnackbar(
                    binding.root,
                    "Register successful, activation email sent!"
                )
                Navigator.replaceFragment(LoginFragment())
            } else {
                requireActivity().longSnackbar(binding.root, message)
            }
        }

        registerViewModel.register(RegisterBody(username, password, email, phoneNum.toLong()))

    }

    private fun setUpChangeEvents() {
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
        binding.emailBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.emailBoxLayout.error = null
            } else {
                binding.emailBoxLayout.error = "Please provide an email"
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