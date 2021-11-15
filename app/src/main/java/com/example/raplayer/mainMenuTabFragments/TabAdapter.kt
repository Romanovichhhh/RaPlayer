package com.example.raplayer.mainMenuTabFragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class TabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {TracksTabFragment()}
            1 -> {AlbumsTabFragment()}
            2 -> {ArtistsTabFragment()}
            else -> { return FoldersTabFragment()}
        }
    }

}