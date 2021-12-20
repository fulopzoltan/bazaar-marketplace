package com.example.bazaar_marketplace.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bazaar_marketplace.R
import com.example.bazaar_marketplace.adapters.FareItemAdapter
import com.example.bazaar_marketplace.databinding.FragmentTimelineBinding
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.*
import com.example.bazaar_marketplace.viewModels.product.ProductViewModel
import com.example.bazaar_marketplace.viewModels.product.ProductViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class TimelineFragment : Fragment() {

    private lateinit var binding: FragmentTimelineBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var adapter: FareItemAdapter
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Getting shared preference instance
        sharedPreferences = requireActivity().application.getSharedPreferences(
            Constants.SHARED_PREF_KEY,
            Context.MODE_PRIVATE
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Show toolbar and nav

        requireActivity().findViewById<Toolbar>(R.id.toolbar).show()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation).show()

        //Binding
        binding = FragmentTimelineBinding.inflate(inflater, container, false)

        //View model
        productViewModel = ViewModelProvider(
            requireActivity(),
            ProductViewModelFactory(Repository())
        )[ProductViewModel::class.java]
        adapter = FareItemAdapter(emptyList())
        binding.fareRecyclerView.adapter = adapter

        productViewModel.products.observe(viewLifecycleOwner) {
            if(productViewModel.products.value?.body()!!.products.isNotEmpty()){
                binding.progressBar.remove()
            }else{
                binding.progressBar.show()
            }
        }

        productViewModel.products.observe(viewLifecycleOwner) { response->
            if(response.isSuccessful) {
                adapter.setData(productViewModel.products.value?.body()!!.products)
                adapter.notifyDataSetChanged()
            }
        }
        binding.fareRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        val token = sharedPreferences.getToken()
        productViewModel.getProducts(token!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


}