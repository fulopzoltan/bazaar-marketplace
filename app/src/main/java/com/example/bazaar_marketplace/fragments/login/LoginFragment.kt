package com.example.bazaar_marketplace.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar_marketplace.R
import com.example.bazaar_marketplace.databinding.FragmentLoginBinding
import com.example.bazaar_marketplace.fragments.TimelineFragment
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.Navigator
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

        binding.logInButton.setOnClickListener {
            Navigator.replaceFragment(TimelineFragment())
            requireActivity().findViewById<Toolbar>(R.id.toolbar).show()
        }

        return binding.root
    }

}