package com.example.hackathon2021.util

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Preference(context: Context) {
    private val prefNm = "mPref"
    private val prefs = context.getSharedPreferences(prefNm, MODE_PRIVATE)

    var token: String?
        get() = prefs.getString("token","")
        set(value) {
            prefs.edit().putString("token", value).apply()
        }
}