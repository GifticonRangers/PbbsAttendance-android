package com.example.data.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceUtil @Inject constructor(@ApplicationContext context: Context) {
    private val preference: SharedPreferences =
        context.getSharedPreferences("TokenPreference", Context.MODE_PRIVATE)

    fun getValue(key: String): String {
        return preference.getString(key, "").toString()
    }

    fun setValue(key: String, str: String) {
        preference.edit().putString(key, str).apply()
    }
}