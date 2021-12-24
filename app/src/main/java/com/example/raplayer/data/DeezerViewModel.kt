package com.example.raplayer.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raplayer.data.models.DTrack
import kotlinx.coroutines.launch

//class DeezerViewModel(private val repository : DeezerRepository): ViewModel() {
//
//    var myResponse: MutableList<DTrack>? = null
//
//    fun getDeezerPlaylist() {
//        viewModelScope.launch {
//            val response = repository.getDeezerPlaylist()
//            myResponse = response
//        }
//    }
//
//}