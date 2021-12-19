package com.example.bazaar_marketplace.utils

import android.app.Activity
import android.text.TextUtils
import android.view.View
import com.google.android.material.snackbar.Snackbar

// VIEW RELATED
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.remove() {
    this.visibility = View.GONE
}

// SNACKBAR UTILS
fun Activity.longSnackbar(view: View, message: String) =
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()

fun Activity.shortSnackbar(view: View, message: String) =
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()

// INPUT VALIDATION

fun isRequiredFieldAndNotEmpty(inputField: String): Boolean {
    return !TextUtils.isEmpty(inputField)
}