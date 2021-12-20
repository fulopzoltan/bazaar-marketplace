package com.example.bazaar_marketplace

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.bazaar_marketplace.fragments.login.LoginFragment
import com.example.bazaar_marketplace.utils.Constants
import com.example.bazaar_marketplace.utils.Navigator
import com.example.bazaar_marketplace.utils.clearAllData


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigator.updateContext(this, R.id.fragment_container_view, supportFragmentManager)

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
    }
}