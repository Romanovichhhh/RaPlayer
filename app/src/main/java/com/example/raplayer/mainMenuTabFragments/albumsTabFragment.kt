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
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote

lateinit var waveformSeekBar : WaveformSeekBar

class AlbumsTabFragment : Fragment() {

    object SpotifyService {
        private const val CLIENT_ID = "50b73cbfe3374795bf94b2a84d35443d"
        private const val  REDIRECT_URI = "com.tolkiana.spotifyplayer://callback"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_albums_tab, container, false)


        return view
    }




}