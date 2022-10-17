package com.example.seonsijo.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

var gradeNum = 1
var classNum = 1
var device_token: String? = null
var gradeClassCheck = false

@HiltAndroidApp
class MyApplication : Application() {
    companion object{
        lateinit var prefs: PreferencesUtil
    }

    override fun onCreate()
    {
        prefs = PreferencesUtil(applicationContext)
        super.onCreate()
    }
}

class PreferencesUtil(context: Context){
    private val prefs: SharedPreferences = context.getSharedPreferences("shared",0)

    fun getString(key: String, defValue: String): String
    {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String)
    {
        prefs.edit().putString(key, str).apply()
    }

    fun getBoolean(key: String, bool: Boolean): Boolean
    {
        return prefs.getBoolean(key,bool).toString().toBoolean()
    }

    fun setBoolean(key: String, bool: Boolean)
    {
        prefs.edit().putBoolean(key,bool).apply()
    }
}