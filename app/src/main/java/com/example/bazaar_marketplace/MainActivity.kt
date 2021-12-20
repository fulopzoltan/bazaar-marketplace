package com.example.bazaar_marketplace

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.bazaar_marketplace.fragments.TimelineFragment
import com.example.bazaar_marketplace.fragments.login.LoginFragment
import com.example.bazaar_marketplace.utils.Constants
import com.example.bazaar_marketplace.utils.Navigator
import com.example.bazaar_marketplace.utils.clearAllData
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigator.updateContext(this, R.id.fragment_container_view, supportFragmentManager)

        bottomNav = findViewById(R.id.bottom_navigation)

        val logOutIcon = findViewById<ImageView>(R.id.logOutIcon)
        logOutIcon.setOnClickListener {
            sharedPreferences = this.application.getSharedPreferences(
                Constants.SHARED_PREF_KEY,
                Context.MODE_PRIVATE
            )
            sharedPreferences.clearAllData()

            Log.d("SHARED", sharedPreferences.toString())
            Navigator.replaceFragment(LoginFragment())
        }

        bottomNav.setOnNavigationItemSelectedListener() { item ->

            when (item.itemId) {
                R.id.timelinePage -> {
                    Navigator.replaceFragment(TimelineFragment())
                    true
                }
                R.id.myMarketPage -> {
                    Navigator.replaceFragment(TimelineFragment())
                    true
                }
                R.id.myFarePage -> {
                    Navigator.replaceFragment(TimelineFragment())
                    true
                }
                else -> {
                    Navigator.replaceFragment(TimelineFragment())
                    true
                }
            }
        }
    }
}