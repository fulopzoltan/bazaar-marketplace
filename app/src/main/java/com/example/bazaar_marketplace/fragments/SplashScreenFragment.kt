package com.example.bazaar_marketplace.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bazaar_marketplace.R
import com.example.bazaar_marketplace.MainActivity

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat.postDelayed


class SplashScreenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).visibility = View.GONE
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, TimelineFragment())
                transaction.commit()
                requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).visibility = View.VISIBLE
            },
            3000
        )
    }
}