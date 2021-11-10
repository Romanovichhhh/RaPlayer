package com.example.raplayer

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.raplayer.tabFragments.SlideAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.properties.Delegates

private lateinit var adapter: SlideAdapter
private lateinit var viewPager: ViewPager2
private lateinit var tabLayout: TabLayout
private lateinit var sharedPreferences: SharedPreferences
private var session by Delegates.notNull<Boolean>()

private val tabNames: Array<String> = arrayOf("Вход", "Регистрация")

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_gate)

        adapter = SlideAdapter(this)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = adapter

        tabLayout = findViewById((R.id.tab_layout))
        TabLayoutMediator(tabLayout, viewPager) {
            tab, position -> tab.text = tabNames[position]
        }.attach()


        sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        session = sharedPreferences.getBoolean("BOOLEAN_KEY", false)
//        if (session) {
//            //Открываем другую активити
//            setContentView(R.layout.activity_main_menu)
//        }
    }






}

