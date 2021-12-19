package com.example.bazaar_marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bazaar_marketplace.utils.Navigator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigator.updateContext(this,R.id.fragment_container_view,supportFragmentManager)
        Navigator.printContext()
    }
}