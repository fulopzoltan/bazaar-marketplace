package com.example.bazaar_marketplace.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import com.example.bazaar_marketplace.interfaces.FareItemClickListeners
import com.example.bazaar_marketplace.models.Product
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

    private val fareItemClickListeners = object : FareItemClickListeners {
        override fun onDeleteClicked(pos: Int, productId: String) {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setMessage("Are you sure you want to Delete?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    productViewModel.deleteResponse.observe(viewLifecycleOwner) { response ->
                        if (response.isSuccessful) {
                            adapter.notifyItemRemoved(pos)
                            productViewModel.products.value?.body()?.products?.removeAt(pos)
                            productViewModel.deleteResponse.removeObservers(viewLifecycleOwner)
                            requireActivity().shortSnackbar(binding.root, "Delete successful!")
                        }
                    }
                    productViewModel.deleteProduct(sharedPreferences.getToken()!!, productId, pos)
                }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        override fun onCardClicked(product: Product) {
            productViewModel.selectForDetail(product)
        }
    }

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
        adapter = FareItemAdapter(mutableListOf(), fareItemClickListeners, "")
        binding.fareRecyclerView.adapter = adapter

        productViewModel.products.observe(viewLifecycleOwner) {

            productViewModel.products.value?.body()?.let { productsResponse ->
                if (productsResponse.products.isNotEmpty()) {
                    binding.progressBar.hide()
                } else {
                    binding.progressBar.show()
                }
            }
        }

        productViewModel.products.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful && productViewModel.products.value?.body() != null) {
                val username = sharedPreferences.getUsername()
                adapter.setData(productViewModel.products.value?.body()!!.products, username!!)
                adapter.notifyDataSetChanged()
            }
        }
        binding.fareRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        val token = sharedPreferences.getToken()
        productViewModel.getProducts(token!!)
        return binding.root
    }
}