package com.example.raplayer.mainMenuTabFragments

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.raplayer.R
import com.example.raplayer.data.SongInfo
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.*
import java.io.File
import java.lang.Exception
import java.util.ArrayList



class TracksTabFragment : Fragment() {

    var listSong = ArrayList<SongInfo>()
    var mediaPlayer : MediaPlayer? = null
    var adapter : ListAdapter? = null


    @SuppressLint("SdCardPath")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_tracks_tab, container, false)

        val rootPath = "/sdcard/Music"

        //var Spot


        val adapter = getPlayList(rootPath)?.let { ListAdapter(it, mediaPlayer, requireContext(), view, findNavController()) }
        val recyclerView = view.listViewTracks
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val a = arguments?.getString("songTitle")
        view.presentTrack.text = arguments?.getString("songTitle")
        mediaPlayer?.setDataSource(arguments?.getString("URL"))
    }

    fun getPlayList(rootPath : String) : ArrayList<SongInfo>? {
        try {
            val rootFolder = File(rootPath)
            val files: Array<File>? = rootFolder.listFiles() //here you will get NPE if directory doesn't contains  any file,handle it like this.
            if (files != null) {
                for (file in files) {

                     if (file.name.endsWith(".mp3") && !file.name.endsWith(".thumbnails")) {

                        listSong.add(SongInfo(file.name, file.absolutePath))
                    }
                }
            }
            return listSong
        } catch (e: Exception) {
            return null
        }
    }


}