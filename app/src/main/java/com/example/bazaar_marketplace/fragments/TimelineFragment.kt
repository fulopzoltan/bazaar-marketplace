package com.example.bazaar_marketplace.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bazaar_marketplace.adapters.FareItemAdapter
import com.example.bazaar_marketplace.databinding.FragmentTimelineBinding
import com.example.bazaar_marketplace.repository.Repository
import com.example.bazaar_marketplace.viewModels.product.ProductViewModel
import com.example.bazaar_marketplace.viewModels.product.ProductViewModelFactory

class TimelineFragment : Fragment() {

    private lateinit var binding: FragmentTimelineBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var adapter: FareItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimelineBinding.inflate(inflater, container, false)
        productViewModel = ViewModelProvider(
            requireActivity(),
            ProductViewModelFactory(Repository())
        )[ProductViewModel::class.java]
        adapter = FareItemAdapter(emptyList())
        binding.fareRecyclerView.adapter = adapter
        productViewModel.products.observe(viewLifecycleOwner) {
            adapter.setData(productViewModel.products.value!!.products)
            adapter.notifyDataSetChanged()
        }
        binding.fareRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        productViewModel.getProducts()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


}