package com.example.raplayer.data

import android.content.Context
import android.content.SharedPreferences



class SharedPrefs(context: Context) {

    var sharedPref : SharedPreferences? = null;

    private fun openPrefs(context: Context){
        sharedPref = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
    }

    fun saveSession(context: Context) {
        openPrefs(context)
        val editor = sharedPref?.edit()
        editor?.apply{putBoolean("BOOLEAN_KEY", true)}?.apply()
        sharedPref = null
    }

    fun isSession(context: Context) : Boolean{
        var session = false
        openPrefs(context)
        session = sharedPref!!.getBoolean("BOOLEAN_KEY", false)
        return session
    }

}