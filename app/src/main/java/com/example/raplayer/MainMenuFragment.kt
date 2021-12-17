package com.example.raplayer

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.raplayer.data.SongInfo
import com.example.raplayer.gateTabFragments.SlideAdapterGate

import com.example.raplayer.mainMenuTabFragments.ListAdapter
import com.example.raplayer.mainMenuTabFragments.TabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.*
import java.io.File
import java.lang.Exception
import java.util.ArrayList


class MainMenuFragment : Fragment() {


    private lateinit var tabAdapter: TabAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private val tabNames: Array<String> = arrayOf("Плейлист", "Трек")
    var listSong = ArrayList<SongInfo>()
    var mediaPlayer : MediaPlayer? = MediaPlayer()
    var adapter : ListAdapter? = null


    @SuppressLint("SdCardPath")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main_menu, container, false)

        permissionStorageSetup()

        tabAdapter = TabAdapter(this)
        viewPager = view.findViewById(R.id.menu_viewPager)
        viewPager.adapter = tabAdapter

        tabLayout = view.findViewById(R.id.menu_tabLayout)
        TabLayoutMediator(tabLayout, viewPager) {
                tab, position -> tab.text = tabNames[position]
        }.attach()

        val rootPath = "/sdcard/Music"

        //var Spot






        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val a = arguments?.getString("songTitle")
//        val с = arguments?.getString("URL")
//        val b = arguments?.getBoolean("Playing")
//        view.presentTrack.text = arguments?.getString("songTitle")
//
////        mediaPlayer?.setDataSource(arguments?.getString("URL"))
//        if (arguments?.getBoolean("Playing",false) == true) {
//            view.bottomPanel.visibility = View.VISIBLE
//        }
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





    private fun permissionStorageSetup() {
        val storagePermission = ContextCompat.checkSelfPermission(
            requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            permissionsResultCallback.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private val permissionsResultCallback = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) {
        when (it) {
            true -> {

                Toast.makeText(requireContext(), "Permission allowed", Toast.LENGTH_SHORT).show()

            }
            false -> {
                Toast.makeText(requireContext(), "Музыка не доступна", Toast.LENGTH_SHORT).show()

            }
        }
    }

}