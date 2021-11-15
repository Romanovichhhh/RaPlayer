package com.example.raplayer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.raplayer.data.SharedPrefs
import kotlin.properties.Delegates






class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val sharedPrefs = SharedPrefs(this)
        if (sharedPrefs.isSession(this)) {
            //Открываем сразу фрашмент главного меню
            navController.navigate(R.id.action_enterFragment_to_mainMenuFragment)
        }
    }


}

