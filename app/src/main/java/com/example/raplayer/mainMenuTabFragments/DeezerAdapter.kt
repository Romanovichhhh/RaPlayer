package com.example.raplayer.mainMenuTabFragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.raplayer.R
import com.example.raplayer.data.SongInfo
import com.example.raplayer.data.models.Model
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.ArrayList

class DeezerAdapter() : RecyclerView.Adapter<DeezerAdapter.MyViewHolder>() {

    var model : Model? = null
    var mySongList = ArrayList<SongInfo>()

    var view: View? = null


    constructor(mySongList: ArrayList<SongInfo>, context: Context, view: android.view.View) : this() {
        this.mySongList = mySongList
        this.model = Model(context)
        this.view = view

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

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