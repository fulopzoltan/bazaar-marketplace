package com.example.bazaar_marketplace


import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.bazaar_marketplace.fragments.MyMarketFragment
import com.example.bazaar_marketplace.fragments.TimelineFragment
import com.example.bazaar_marketplace.fragments.profile.ProfileFragment
import com.example.bazaar_marketplace.utils.Navigator
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var profileIcon: ImageView
    private lateinit var filterIcon: ImageView
    private lateinit var searchIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigator.updateContext(this, R.id.fragment_container_view, supportFragmentManager)

        bottomNav = findViewById(R.id.bottom_navigation)
        profileIcon = findViewById(R.id.profileIcon)
        filterIcon = findViewById(R.id.filterIcon)
        searchIcon = findViewById(R.id.searchIcon)

        initBottomNav()
        initToolbar()
    }


    private fun initBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener() { item ->

            when (item.itemId) {
                R.id.timelinePage -> {
                    Navigator.replaceFragment(TimelineFragment(), true)
                    true
                }
                R.id.myMarketPage -> {
                    Navigator.replaceFragment(MyMarketFragment(), true)
                    true
                }
                R.id.myFarePage -> {
                    Navigator.replaceFragment(TimelineFragment(), true)
                    true
                }
                else -> {
                    Navigator.replaceFragment(TimelineFragment(), true)
                    true
                }
            }
        }
    }

    private fun initToolbar() {

        profileIcon.setOnClickListener {
            Navigator.replaceFragment(ProfileFragment(), true)

        }

        filterIcon.setOnClickListener {

        }

        searchIcon.setOnClickListener {

        }
    }
}