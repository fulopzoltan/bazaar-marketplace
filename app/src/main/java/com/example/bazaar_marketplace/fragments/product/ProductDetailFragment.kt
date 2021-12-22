package com.example.bazaar_marketplace.fragments.product

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar_marketplace.databinding.FragmentProductDetailBinding
import com.example.bazaar_marketplace.models.Product
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.Constants
import com.example.bazaar_marketplace.utils.Navigator
import com.example.bazaar_marketplace.utils.getUsername
import com.example.bazaar_marketplace.utils.show
import com.example.bazaar_marketplace.viewModels.product.ProductViewModel
import com.example.bazaar_marketplace.viewModels.product.ProductViewModelFactory

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var currentProduct: Product
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
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        productViewModel = ViewModelProvider(
            requireActivity(),
            ProductViewModelFactory(Repository())
        )[ProductViewModel::class.java]

        currentProduct = productViewModel.selectedForDetail

        binding.usernameText.text = currentProduct.username
        binding.titleText.text = currentProduct.title
        binding.priceText.text = currentProduct.pricePerUnit
        binding.currencyText.text = "${currentProduct.priceType}/"
        binding.unitText.text = currentProduct.amountType
        when (currentProduct.isActive) {
            true -> binding.fareItemActive.show()
            else -> binding.fareItemInactive.show()
        }
        val totalString = "${currentProduct.units} <small>${currentProduct.amountType}</small>."
        binding.totalItemCircle.text =
            HtmlCompat.fromHtml(totalString, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val priceString =
            "${currentProduct.pricePerUnit} <small>${currentProduct.priceType}</small>."
        binding.priceCircle.text =
            HtmlCompat.fromHtml(priceString, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val soldString = "0 <small>${currentProduct.amountType}</small>."
        binding.soldCircle.text =
            HtmlCompat.fromHtml(soldString, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val revenueString = "0 <small>${currentProduct.priceType}</small>."
        binding.revenueCircle.text =
            HtmlCompat.fromHtml(revenueString, HtmlCompat.FROM_HTML_MODE_LEGACY)

        binding.descriptionText.text = currentProduct.description

        if (currentProduct.username == sharedPreferences.getUsername()!!) {
            binding.editButton.show()
            binding.editButton.setOnClickListener {
                val fragment = AddProductFragment()
                val args = Bundle()
                args.putBoolean("EDITING", true)
                fragment.arguments = args
                Navigator.replaceFragment(fragment, true)
            }
        }
        return binding.root
    }
}