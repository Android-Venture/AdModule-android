package com.sofit.adsimplementationhelper.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sofit.adsimplementationhelper.R

class LogsAdapter(val logList:ArrayList<String>) : RecyclerView.Adapter<LogsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.log_item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Bind data to the view holder

        holder.class_name_txt.text = position.toString()+" "+logList.get(position)
    }

    override fun getItemCount(): Int {
        // Return the number of items in the RecyclerView
        return logList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define views and data binding here

        val class_name_txt = itemView.findViewById<TextView>(R.id.class_name_txt)
    }
}

