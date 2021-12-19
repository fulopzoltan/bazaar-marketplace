package com.example.bazaar_marketplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bazaar_marketplace.R

import android.os.Handler
import android.os.Looper
import com.example.bazaar_marketplace.utils.Navigator
import com.example.bazaar_marketplace.utils.hide
import com.example.bazaar_marketplace.utils.show


class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).visibility =
            View.GONE
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.splashImage)
                    .hide()
                requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.progressBar)
                    .show()
                requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.loadingText)
                    .show()
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        Navigator.replaceFragment(TimelineFragment())
                        requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
                            .show()
                    },
                    1500
                )
            },
            2000
        )
    }
}