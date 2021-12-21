package com.example.bazaar_marketplace.utils

import android.app.Activity
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Patterns
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

fun isValidEmail(inputField: String): Boolean {
    return !TextUtils.isEmpty(inputField) && Patterns.EMAIL_ADDRESS.matcher(inputField).matches()
}

// SHARED PREF UTILS
fun SharedPreferences.saveToken(token: String) {
    this.edit().putString(Constants.USER_TOKEN, token).apply()
}

fun SharedPreferences.getToken(): String? {
    return this.getString(Constants.USER_TOKEN, "")
}

fun SharedPreferences.saveUsername(username: String) {
    this.edit().putString(Constants.USER_USERNAME, username).apply()
}

fun SharedPreferences.getUsername(): String? {
    return this.getString(Constants.USER_USERNAME, "")
}

fun SharedPreferences.saveEmail(email: String) {
    this.edit().putString(Constants.USER_EMAIL, email).apply()
}

fun SharedPreferences.getEmail(): String? {
    return this.getString(Constants.USER_EMAIL, "")
}

fun SharedPreferences.savePhoneNum(phoneNum: Long) {
    this.edit().putLong(Constants.USER_PHONENUM, phoneNum).apply()
}

fun SharedPreferences.getPhoneNum(): Long {
    return this.getLong(Constants.USER_PHONENUM, 0)
}


fun SharedPreferences.saveTokenCreation(tokenCreation: Long) {
    this.edit().putLong(Constants.USER_TOKEN_CREATION, tokenCreation).apply()
}

fun SharedPreferences.getTokenCreation(): Long {
    return this.getLong(Constants.USER_TOKEN_CREATION, 0)
}


fun SharedPreferences.saveTokenRefresh(tokenRefresh: Long) {
    this.edit().putLong(Constants.USER_TOKEN_REFRESH, tokenRefresh).apply()
}

fun SharedPreferences.getTokenRefresh(): Long {
    return this.getLong(Constants.USER_TOKEN_REFRESH, 0)
}

fun SharedPreferences.clearAllData() {
    val keys = listOf(
        Constants.USER_TOKEN,
        Constants.USER_EMAIL,
        Constants.USER_PHONENUM,
        Constants.USER_TOKEN_CREATION,
        Constants.USER_TOKEN_REFRESH,
        Constants.USER_USERNAME
    )
    val editor = this.edit()
    keys.forEach { key ->
        editor.remove(key)
    }
    editor.apply()
}

// Extension functions to remove " from String

fun String.removeQuote() = this.replace("\"", "")