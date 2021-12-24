package com.example.raplayer.data.api

import com.example.raplayer.data.models.DTrack
import com.example.raplayer.data.models.JSONResponse
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface Deezer {

    //@GET("/playlist/1405240385")
    @GET("/playlist/4328/tracks")
    open fun getJSON(): Call<JSONResponse?>?
    //open fun readJsonArray(): Call<JSONResponse?>?
}