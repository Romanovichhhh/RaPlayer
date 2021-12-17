package com.example.raplayer.data

class SongInfo {

   var Title : String? = null
   // var Artist : String? = null
    var URL : String? = null

    constructor(Title: String?,  SongURL: String?) {
        this.Title = Title
       // this.Artist = Artist
        this.URL = SongURL
    }
}