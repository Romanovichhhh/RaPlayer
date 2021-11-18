package com.example.raplayer.data

import android.content.Context
import android.content.SharedPreferences



class SharedPrefs(context: Context) {

    var sharedPref : SharedPreferences? = null;


        fun getPrefs(context:Context): SharedPreferences {
            return if (sharedPref == null) {
                sharedPref = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                sharedPref
            } else {
                sharedPref
            }!!
        }

    fun saveSession(context: Context) {
        getPrefs(context).edit().putBoolean("BOOLEAN_KEY", true).apply()
    }

    fun isSession(context: Context) : Boolean{
        var session = false
        getPrefs(context).getBoolean("BOOLEAN_KEY", false)
        return session
    }


}