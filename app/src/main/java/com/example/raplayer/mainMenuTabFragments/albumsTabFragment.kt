package com.example.raplayer.mainMenuTabFragments

import android.R.attr
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.raplayer.R
import com.example.raplayer.data.DeezerRepository
//import com.example.raplayer.data.DeezerViewModel
//import com.example.raplayer.data.DeezerViewModelFactory
import com.example.raplayer.data.UserViewModel
import com.example.raplayer.data.api.Deezer
import com.example.raplayer.data.api.RetrofitInstance
import com.example.raplayer.data.models.DTrack
import com.example.raplayer.data.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import com.google.gson.JsonArray

import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import android.R.attr.data
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.raplayer.data.SongInfo
import com.example.raplayer.data.models.JSONResponse
import kotlinx.android.synthetic.main.fragment_albums_tab.view.*
import java.io.File
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


//lateinit var waveformSeekBar : WaveformSeekBar

class AlbumsTabFragment : Fragment() {

    var listSong = java.util.ArrayList<SongInfo>()
    var adapter : DeezerAdapter? = null


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_albums_tab, container, false)

            val rootpath = "/storage/emulated/0/Music"

            adapter = getPlayList(rootpath)?.let {DeezerAdapter(it, requireContext(), view)}
            val recyclerView = view.DeezerRecycler
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())















            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val api: Deezer = RetrofitInstance.retrofit.create(Deezer::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                val call: Call<JSONResponse?>? = api.getJSON()
                call?.enqueue(object : Callback<JSONResponse?> {
                    override fun onResponse(
                        call: Call<JSONResponse?>,
                        response: Response<JSONResponse?>
                    ) {
                        val jsonResponse: JSONResponse? = response.body()
                        val data = ArrayList(listOf(jsonResponse?.getDPlaylist()))
                        Log.d("AAA", (data.size ?: -1).toString())
                    }

                    override fun onFailure(call: Call<JSONResponse?>, t: Throwable) {
                        Log.d("Error", t.message!!)
                    }
                })
            }


            
            
//            val jsonCall: Call<JsonArray?>? = api.readJsonArray()
//            jsonCall?.enqueue(object : Callback<JsonArray?> {
//                override fun onResponse(call: Call<JsonArray?>?, response: Response<JsonArray?>) {
//                    val jsonString: String = response.body().toString()
//                    Log.i("onResponse", jsonString)
//                    val listType: Type = object : TypeToken<List<DTrack?>?>() {}.type
//                    val yourList: List<DTrack> = Gson().fromJson<List<DTrack>>(jsonString, listType)
//                    Log.i("onResponse", yourList.toString())
//                }
//
//                override fun onFailure(call: Call<JsonArray?>?, t: Throwable) {
//                    Log.e("onFailure", t.toString())
//                }
//            })
//            val call: retrofit2.Call<MutableList<DTrack>> =
//            CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    val execute = call.execute()
//
//
//                    Log.d("AAA",(execute?.body()?.size?: - 1).toString())
//                } catch (e: Exception) {
//                    Log.e("AAA", " ", e)
//                }
//                call.enqueue(object : retrofit2.Callback<MutableList<com.alelak.soundroid.models.Track>?> {
//                    override fun onResponse(response: retrofit2.Response<MutableList<com.alelak.soundroid.models.Track>?>?) {
//                        if (response?.isSuccess() == true) {
//                            Log.d("AAA",(response?.body()?.size?: - 1).toString())
//                            //songAdapter.notifyDataSetChanged()
//                        }
//                    }
//
//                    override fun onFailure(t: Throwable?) {
//                        TODO("Not yet implemented")
//                    }
//                })








//            val repository = DeezerRepository()
////            val viewModelFactory = DeezerViewModelFactory(repository)
////            viewModel = ViewModelProvider(this, viewModelFactory)[DeezerViewModel::class.java]
////            viewModel.getDeezerPlaylist()
////            val DeezerPlaylist = viewModel.myResponse
////            Log.d("AAA", DeezerPlaylist?.size.toString())
            return view
        }








    fun getPlayList(rootPath : String) : java.util.ArrayList<SongInfo>? {
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


