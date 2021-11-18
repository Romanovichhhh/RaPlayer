package com.example.raplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.raplayer.gateTabFragments.SlideAdapterGate
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator




private val tabNames: Array<String> = arrayOf("Вход", "Регистрация")

class EnterFragment : Fragment() {

    private lateinit var adapter: SlideAdapterGate
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_enter, container, false)

        adapter = SlideAdapterGate(this)
        viewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = adapter

        tabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) {
                tab, position -> tab.text = tabNames[position]
        }.attach()



        return view
    }



}