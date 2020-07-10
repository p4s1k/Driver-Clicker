package com.example.driverclicker.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.driverclicker.R

class RecyclerViewAdapterStatus(val statuslist: ArrayList<StatusDataModel>): RecyclerView.Adapter<RecyclerViewAdapterStatus.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var View = LayoutInflater.from(parent.context).inflate(R.layout.item_work, parent, false)
        return ViewHolder(View)
    }

    override fun getItemCount(): Int {
        return statuslist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var status:StatusDataModel= statuslist[position]
        holder.tittle.text=status.tittle
        holder.desc.text=status.desc
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        var tittle: TextView = item.findViewById(R.id.cardTittle)
        var desc: TextView = item.findViewById(R.id.cardDescription)

    }
}