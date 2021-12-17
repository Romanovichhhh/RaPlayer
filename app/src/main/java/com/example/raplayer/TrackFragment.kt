package com.example.raplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.raplayer.data.Model
import com.example.raplayer.data.SongInfo


class TrackFragment() : Fragment() {





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_track, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var songInfo = SongInfo(arguments?.getString("songTitle"), arguments?.getString("URL"))

        context?.let { Model(it) }?.BindTrackView(songInfo, view, findNavController())


    }

}