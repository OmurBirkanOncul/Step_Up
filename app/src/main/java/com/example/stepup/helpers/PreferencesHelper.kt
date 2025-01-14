package com.example.stepup.helpers

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    // E-posta ve şifre kaydetme
    fun saveCredentials(email: String, password: String) {
        sharedPreferences.edit().apply {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    // E-posta ve şifre doğrulama
    fun validateLogin(email: String, password: String): Boolean {
        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)
        return email == savedEmail && password == savedPassword
    }

    // Profil bilgilerini kaydetme
    fun saveProfileInfo(name: String, username: String, phoneNumber: String, aboutMe: String) {
        sharedPreferences.edit().apply {
            putString("name", name)
            putString("username", username)
            putString("phoneNumber", phoneNumber)
            putString("aboutMe", aboutMe)
            apply()
        }
    }

    // Profil bilgilerini yükleme
    fun getProfileInfo(): Map<String, String> {
        return mapOf(
            "name" to (sharedPreferences.getString("name", "") ?: ""),
            "username" to (sharedPreferences.getString("username", "") ?: ""),
            "phoneNumber" to (sharedPreferences.getString("phoneNumber", "") ?: ""),
            "aboutMe" to (sharedPreferences.getString("aboutMe", "") ?: "")
        )
    }



    // Bildirim durumu saklama (önceki örneğinizden)
    fun setNotificationsEnabled(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("notifications_enabled", enabled).apply()
    }

    fun areNotificationsEnabled(): Boolean {
        return sharedPreferences.getBoolean("notifications_enabled", true)
    }
}
