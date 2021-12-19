package com.example.bazaar_marketplace.fragments.login

import android.os.Bundle
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
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.Navigator
import com.example.bazaar_marketplace.utils.isRequiredFieldAndNotEmpty
import com.example.bazaar_marketplace.utils.longSnackbar
import com.example.bazaar_marketplace.utils.show
import com.example.bazaar_marketplace.viewModels.login.LoginViewModel
import com.example.bazaar_marketplace.viewModels.login.LoginViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(
            requireActivity(),
            LoginViewModelFactory(Repository())
        )[LoginViewModel::class.java]

        loginViewModel.loginResponseError.observe(viewLifecycleOwner) {
            if (loginViewModel.loginResponseError.value == true) {
                requireActivity().longSnackbar(binding.root, "Invalid credentials")
            }
        }

        loginViewModel.loginResponse.observe(viewLifecycleOwner) {
            if (loginViewModel.loginResponseError.value == false) {
                requireActivity().findViewById<Toolbar>(R.id.toolbar).show()
                requireActivity().longSnackbar(binding.root, "Login Successful")
                Navigator.replaceFragment(TimelineFragment())
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logInButton.setOnClickListener { onLoginClick() }
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

        loginViewModel.login(username, password)
    }
}