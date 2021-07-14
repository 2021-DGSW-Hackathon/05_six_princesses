package com.example.hackathon2021.util

import android.app.Application

class mApplication:Application() {
    companion object{
        lateinit var prefs:Preference
    }
    override fun onCreate() {
        prefs= Preference(applicationContext)
        super.onCreate()
    }
}