package com.example.bazaar_marketplace.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


@SuppressLint("StaticFieldLeak")
object Navigator {
    private var currentActivity: Activity? = null
    private var fragmentContainerId: Int? = null
    private var fragmentManager: FragmentManager? = null

    fun updateContext(
        activity: Activity,
        fragmentContainerId: Int,
        fragmentManager: FragmentManager
    ) {
        currentActivity = activity
        Navigator.fragmentContainerId = fragmentContainerId
        Navigator.fragmentManager = fragmentManager
    }

    fun replaceFragment(newFragment: Fragment, addToBackStack: Boolean = false) {
        if (fragmentManager != null) {
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(fragmentContainerId!!, newFragment)
            if(addToBackStack){
                transaction.addToBackStack(newFragment.javaClass.name)
            }
            transaction.commit()
        }
    }
}