package com.example.bazaar_marketplace.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar_marketplace.databinding.FragmentAddProductBinding
import com.example.bazaar_marketplace.models.AddProductBody
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.*
import com.example.bazaar_marketplace.viewModels.product.ProductViewModel
import com.example.bazaar_marketplace.viewModels.product.ProductViewModelFactory


class AddProductFragment : Fragment() {

    private lateinit var binding: FragmentAddProductBinding
    private lateinit var productViewModel: ProductViewModel
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
        binding = FragmentAddProductBinding.inflate(inflater, container, false)
        productViewModel = ViewModelProvider(
            requireActivity(),
            ProductViewModelFactory(Repository())
        )[ProductViewModel::class.java]

        initStatusSwitch()
        initContactDetails()
        setUpChangeEvents()
        binding.addProductButton.setOnClickListener { onAddProduct() }
        return binding.root
    }

    private fun initContactDetails() {
        binding.contactEmailBox.text =
            Editable.Factory.getInstance().newEditable(sharedPreferences.getEmail())
        binding.contactNameBox.text =
            Editable.Factory.getInstance().newEditable(sharedPreferences.getUsername())
        binding.contactPhoneNumBox.text =
            Editable.Factory.getInstance().newEditable(sharedPreferences.getPhoneNum().toString())
    }

    private fun initStatusSwitch() {
        binding.statusSwitch.setOnClickListener {
            when (binding.statusSwitch.isChecked) {
                true -> {
                    binding.activeCheck.show()
                    binding.inactiveCheck.remove()
                }
                else -> {
                    binding.activeCheck.remove()
                    binding.inactiveCheck.show()
                }
            }
        }
    }

    private fun onAddProduct() {
        var error = false
        val active = binding.statusSwitch.isChecked
        val title = binding.titleBox.text.toString()
        val price = binding.priceBox.text.toString()
        val currency = binding.currencyBox.text.toString()
        val amount = binding.amountBox.text.toString()
        val unit = binding.priceUnitBox.text.toString()
        val description = binding.descriptionBox.text.toString()

        if (!isRequiredFieldAndNotEmpty(title)) {
            error = true;
            binding.titleBoxLayout.error = "Field required*"
        }
        if (!isRequiredFieldAndNotEmpty(price)) {
            error = true;
            binding.priceBoxLayout.error = "Field required*"
        }
        if (!isRequiredFieldAndNotEmpty(currency)) {
            error = true;
            binding.currencyBoxLayout.error = "Field required*"
        }
        if (!isRequiredFieldAndNotEmpty(amount)) {
            error = true;
            binding.amountBoxLayout.error = "Field required*"
        }
        if (!isRequiredFieldAndNotEmpty(unit)) {
            error = true;
            binding.priceUnitBoxLayout.error = "Field required*"
        }

        if (!isRequiredFieldAndNotEmpty(description)) {
            error = true;
            binding.descriptionBoxLayout.error = "Field required*"
        }
        if (error) return;

        productViewModel.addResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                requireActivity().longSnackbar(binding.root, "Product added successfully")
                Navigator.replaceFragment(MyMarketFragment())
            }
        }

        val token = sharedPreferences.getToken()
        productViewModel.addProduct(
            token!!,
            AddProductBody(title, description, price, amount, active, unit, currency, 5.0)
        )
    }

    private fun setUpChangeEvents() {
        binding.titleBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.titleBoxLayout.error = null
            } else {
                binding.titleBoxLayout.error = "Field required*"
            }
        }
        binding.priceBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.priceBoxLayout.error = null
            } else {
                binding.priceBoxLayout.error = "Field required*"
            }
        }
        binding.amountBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.amountBoxLayout.error = null
            } else {
                binding.amountBoxLayout.error = "Field required*"
            }
        }
        binding.currencyBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.currencyBoxLayout.error = null
            } else {
                binding.currencyBoxLayout.error = "Field required*"
            }
        }

        binding.priceUnitBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.priceUnitBoxLayout.error = null
            } else {
                binding.priceUnitBoxLayout.error = "Field required*"
            }
        }

        binding.descriptionBox.doOnTextChanged { inputText, _, _, _ ->
            if (isRequiredFieldAndNotEmpty(inputText.toString())) {
                binding.descriptionBoxLayout.error = null
            } else {
                binding.descriptionBoxLayout.error = "Field required*"
            }
        }
    }

}