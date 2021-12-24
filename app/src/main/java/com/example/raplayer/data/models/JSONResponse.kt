package com.example.raplayer.data.models

class JSONResponse {
    private  var DPlaylist : MutableList<DTrack>? = null

    fun getDPlaylist(): MutableList<DTrack>? {
            return DPlaylist
        }
}