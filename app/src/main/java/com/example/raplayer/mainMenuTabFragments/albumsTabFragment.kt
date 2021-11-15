package com.example.raplayer.mainMenuTabFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.raplayer.R
import com.masoudss.lib.WaveformSeekBar
import com.masoudss.lib.utils.Utils
import com.masoudss.lib.utils.WaveGravity
import java.io.File

lateinit var waveformSeekBar : WaveformSeekBar

class AlbumsTabFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_albums_tab, container, false)


        return view
    }




}