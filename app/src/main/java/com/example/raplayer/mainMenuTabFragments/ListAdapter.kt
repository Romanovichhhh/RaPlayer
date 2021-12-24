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
import com.example.raplayer.data.models.Model
import android.view.View as ViewView

class ListAdapter() : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    var model : Model? = null
    var mySongList = ArrayList<SongInfo>()

    var view: ViewView? = null


    constructor(mySongList: ArrayList<SongInfo>, context: Context, view: android.view.View) : this() {
        this.mySongList = mySongList
        this.model = Model(context)
        this.view = view

    }



    class MyViewHolder(itemView: ViewView) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = mySongList[position]
        holder.itemView.songName.text = currentItem.Title.toString()



        holder.itemView.setOnClickListener {

            view?.let { it1 -> model?.playTrack(holder.itemView, currentItem, it1, mySongList) }

        }
    }

    override fun getItemCount(): Int {
        return mySongList.size
    }


}


