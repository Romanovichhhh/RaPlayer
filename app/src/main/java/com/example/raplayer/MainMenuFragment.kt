package com.example.raplayer

import android.icu.text.Transliterator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.raplayer.gateTabFragments.SlideAdapterGate
import com.example.raplayer.mainMenuTabFragments.TabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


private lateinit var adapter: TabAdapter
private lateinit var viewPager: ViewPager2
private lateinit var tabLayout: TabLayout

private val tabNames: Array<String> = arrayOf("Треки", "Альбомы", "Исполнители", "Папки")

class MainMenuFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main_menu, container, false)

        adapter = TabAdapter(this)
        viewPager = view.findViewById(R.id.menu_viewPager)
        viewPager.adapter = adapter

        tabLayout = view.findViewById(R.id.menu_tabLayout)
        TabLayoutMediator(tabLayout, viewPager) {
            tab, position -> tab.text = tabNames[position]
        }.attach()

        findNavController().navigate(R.id.action_mainMenuFragment_to_trackFragment)

        return view
    }



}