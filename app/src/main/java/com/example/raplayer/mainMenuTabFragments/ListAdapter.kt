package com.example.raplayer.mainMenuTabFragments

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.raplayer.R
import com.example.raplayer.data.SongInfo
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.ArrayList
import com.example.raplayer.data.Model
import android.view.View as ViewView

class ListAdapter() : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    var model : Model? = null
    var mySongList = ArrayList<SongInfo>()
    var mp : MediaPlayer? = null
    var view: ViewView? = null
    lateinit  var navController: NavController

    constructor(mySongList: ArrayList<SongInfo>, mp: MediaPlayer?, context: Context, view: android.view.View, navController: NavController) : this() {
        this.mySongList = mySongList
        this.mp = mp
        this.model = Model(context)
        this.view = view
        this.navController = navController
    }



    class MyViewHolder(itemView: ViewView) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = mySongList[position]
        //val goToTrackFragmentBtn = TracksTabFragment.presentTrack
        holder.itemView.songName.text = currentItem.Title.toString()



        holder.itemView.setOnClickListener {

            view?.let { it1 -> model?.playTrack(holder.itemView, currentItem, it1, navController) }

        }
    }

    override fun getItemCount(): Int {
        return mySongList.size
    }


}


