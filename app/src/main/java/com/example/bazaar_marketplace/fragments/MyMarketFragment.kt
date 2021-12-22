package com.example.bazaar_marketplace.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bazaar_marketplace.R
import com.example.bazaar_marketplace.adapters.FareItemAdapter
import com.example.bazaar_marketplace.databinding.FragmentMyMarketBinding
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.*
import com.example.bazaar_marketplace.viewModels.product.ProductViewModel
import com.example.bazaar_marketplace.viewModels.product.ProductViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyMarketFragment : Fragment() {

    private lateinit var binding: FragmentMyMarketBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var adapter: FareItemAdapter
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


        binding = FragmentMyMarketBinding.inflate(inflater, container, false)
        productViewModel = ViewModelProvider(
            requireActivity(),
            ProductViewModelFactory(Repository())
        )[ProductViewModel::class.java]

        adapter = FareItemAdapter(emptyList(), "")
        binding.myFareRecyclerView.adapter = adapter
        binding.myFareRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        val username = sharedPreferences.getUsername()
        productViewModel.myProducts.observe(viewLifecycleOwner) { response ->
            binding.progressBar.hide()
            if (response.isSuccessful) {
                productViewModel.myProducts.value?.body()?.let { productsResponse ->
                    adapter.setData(productsResponse.products, username!!)
                    adapter.notifyDataSetChanged()

                    if (productsResponse.products.isNotEmpty()) {
                        binding.emptyText.hide()
                    } else {
                        binding.emptyText.show()
                    }
                }
            }
        }
        val token = sharedPreferences.getToken()
        productViewModel.getMyProducts(token!!, username!!)

        return binding.root
    }

}