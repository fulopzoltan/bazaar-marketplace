package com.example.bazaar_marketplace.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bazaar_marketplace.adapters.FareItemAdapter
import com.example.bazaar_marketplace.databinding.FragmentMyMarketBinding
import com.example.bazaar_marketplace.fragments.product.AddProductFragment
import com.example.bazaar_marketplace.fragments.product.ProductDetailFragment
import com.example.bazaar_marketplace.interfaces.FareItemClickListeners
import com.example.bazaar_marketplace.models.Product
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.utils.*
import com.example.bazaar_marketplace.viewModels.product.ProductViewModel
import com.example.bazaar_marketplace.viewModels.product.ProductViewModelFactory

class MyMarketFragment : Fragment() {

    private lateinit var binding: FragmentMyMarketBinding
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
                            productViewModel.myProducts.value?.body()?.products?.removeAt(pos)
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
            Navigator.replaceFragment(ProductDetailFragment(), true)
        }
    }

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

        adapter = FareItemAdapter(mutableListOf(), fareItemClickListeners, "")
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


        binding.addActionButton.setOnClickListener {
            Navigator.replaceFragment(AddProductFragment(), true)
        }

        return binding.root
    }

}