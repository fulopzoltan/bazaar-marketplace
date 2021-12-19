package com.example.bazaar_marketplace

import android.app.Application
import com.example.bazaar_marketplace.utils.Navigator

object MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Navigator.printContext()
    }
}