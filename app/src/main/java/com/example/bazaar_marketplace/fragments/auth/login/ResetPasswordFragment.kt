package com.example.bazaar_marketplace.fragments.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar_marketplace.databinding.FragmentResetPasswordBinding
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.Navigator
import com.example.bazaar_marketplace.utils.isRequiredFieldAndNotEmpty
import com.example.bazaar_marketplace.utils.isValidEmail
import com.example.bazaar_marketplace.utils.longSnackbar
import com.example.bazaar_marketplace.viewModels.login.LoginViewModel
import com.example.bazaar_marketplace.viewModels.login.LoginViewModelFactory


class ResetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(
            requireActivity(),
            LoginViewModelFactory(Repository())
        )[LoginViewModel::class.java]

        setUpOnChange()
        binding.emailMeButton.setOnClickListener { onEmailMe() }

        return binding.root
    }

    private fun onEmailMe() {
        var error = false;
        val email = binding.emailBox.text.toString()
        val username = binding.usernameBox.text.toString()
        if (!isValidEmail(email)) {
            error = true;
            binding.emailBoxLayout.error = "Please provide a valid email!"
        }
        if (!isRequiredFieldAndNotEmpty(username)) {
            error = true;
            binding.usernameBoxLayout.error = "Please provide a username!"
        }
        if (error) return;

        loginViewModel.resetPasswordResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.let { it ->
                    requireActivity().longSnackbar(binding.root, it.message)
                    Navigator.replaceFragment(LoginFragment())
                }
            }
        }
        loginViewModel.resetPassword(username, email)
    }


    private fun setUpOnChange() {
        binding.emailBox.doOnTextChanged { inputText, _, _, _ ->
            if (isValidEmail(inputText.toString())) {
                binding.emailBoxLayout.error = null
            } else {
                binding.emailBoxLayout.error = "Please provide a valid email!"
            }
        }

        binding.usernameBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.usernameBoxLayout.error = null
            } else {
                binding.usernameBoxLayout.error = "Please provide a username!"
            }
        }
    }
}