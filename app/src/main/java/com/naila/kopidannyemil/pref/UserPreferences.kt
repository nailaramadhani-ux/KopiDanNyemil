package com.naila.kopidannyemil.pref

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveUser(fullName: String, username: String, password: String) {
        prefs.edit().apply {
            putString(KEY_FULL_NAME, fullName)
            putString(KEY_USERNAME, username)
            putString(KEY_PASSWORD, password)
            apply()
        }
    }

    fun updateFullName(fullName: String) {
        prefs.edit().putString(KEY_FULL_NAME, fullName).apply()
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun getFullName(): String = prefs.getString(KEY_FULL_NAME, "Pengguna") ?: "Pengguna"

    fun getUsername(): String = prefs.getString(KEY_USERNAME, "") ?: ""

    fun getPassword(): String = prefs.getString(KEY_PASSWORD, "") ?: ""

    fun validateLogin(inputUser: String, inputPass: String): Boolean {
        val savedUser = getUsername()
        val savedPass = getPassword()

        if (savedUser.isNotEmpty() && savedPass.isNotEmpty()) {
            if (inputUser == savedUser && inputPass == savedPass) {
                return true
            }
        }
        // Fallback dummy user login if matches dummy credentials
        return (inputUser == "user" && inputPass == "12345") || (inputUser == "admin" && inputPass == "12345")
    }

    fun logout() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "kopi_nyemil_prefs"
        private const val KEY_FULL_NAME = "key_full_name"
        private const val KEY_USERNAME = "key_username"
        private const val KEY_PASSWORD = "key_password"
        private const val KEY_IS_LOGGED_IN = "key_is_logged_in"
    }
}
