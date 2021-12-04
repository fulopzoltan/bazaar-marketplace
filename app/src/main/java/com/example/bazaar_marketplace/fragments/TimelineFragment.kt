package com.example.bazaar_marketplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bazaar_marketplace.R
import com.example.bazaar_marketplace.adapters.FareDummy
import com.example.bazaar_marketplace.adapters.FareItemAdapter
import com.example.bazaar_marketplace.databinding.FragmentTimelineBinding

class TimelineFragment : Fragment() {

    private lateinit var binding: FragmentTimelineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimelineBinding.inflate(inflater, container, false)
        val dummyList = generateDummyList(100);
        binding.fareRecyclerView.adapter = FareItemAdapter(dummyList)
        binding.fareRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


    private fun generateDummyList(size: Int): List<FareDummy> {

        val list = ArrayList<FareDummy>()
        for (i in 0 until size) {
            val item = FareDummy("Kristina Watson","P{alinka}de prune", 25, "Ron/L")
            list.add(item)
        }
        return list
    }


}