package com.example.raplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.masoudss.lib.WaveformSeekBar
import kotlinx.android.synthetic.main.fragment_track.*
import java.io.File
import java.util.jar.Manifest


class TrackFragment : Fragment() {

    private var waveformView : WaveformSeekBar? = null

    private val readPermissionResult = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if(permissions.all { true }) {
            waveformView?.setSampleFrom("/storage/emulated/0/Music/DEF LEPPARD - Love Bites.mp3")
        }
    }


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
        waveformView = view.findViewById(R.id.waveformSeekBar)
        readPermissionResult.launch(arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ))
    }

}