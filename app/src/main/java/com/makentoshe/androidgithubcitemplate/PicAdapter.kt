package com.makentoshe.androidgithubcitemplate

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_pic.view.*
import java.io.File

class PicAdapter(private val pics: List<Pic>, private val activity: LoadingScreen):
    RecyclerView.Adapter<PicAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(picture: Pic){
            itemView.image.setImageURI(Uri.fromFile(File(picture.imageUri)))
            itemView.setOnClickListener {
                Toast.makeText(activity, "Выбор подтвержден", Toast.LENGTH_SHORT).show()
                activity.setPicture(picture.imageUri)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.item_pic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picture : Pic = pics[position]
        holder.bind(picture)
    }

    override fun getItemCount(): Int {
        return pics.size
    };
}