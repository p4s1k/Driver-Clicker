package com.example.driverclicker.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.driverclicker.R

class RecyclerViewAdapterWork(val worklist: ArrayList<WorkDataModel>):
      RecyclerView.Adapter<RecyclerViewAdapterWork.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var View = LayoutInflater.from(parent.context).inflate(R.layout.item_work, parent, false)
        return ViewHolder(View)
    }

    override fun getItemCount(): Int {
        return worklist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var work:WorkDataModel= worklist[position]
        holder.tittle.text=work.tittle
        holder.desc.text=work.desc
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        var tittle:TextView = item.findViewById(R.id.cardTittle)
        var desc:TextView = item.findViewById(R.id.cardDescription)
    }

}