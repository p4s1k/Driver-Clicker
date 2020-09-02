package com.example.driverclicker.fragments.recyclerViews

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.driverclicker.R
import com.example.driverclicker.fragments.recyclerViews.data.StatusDataModel

//RV for MenuOne/Two/Three()
class RecyclerViewAdapterStatus(val statuslist: ArrayList<StatusDataModel>): RecyclerView.Adapter<RecyclerViewAdapterStatus.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var View = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(
            View
        )
    }

    override fun getItemCount(): Int {
        return statuslist.size
    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var status: StatusDataModel = statuslist[position]
        holder.tittle.text=status.tittle
        holder.desc.text=status.desc
        if (!status.achieved){
            holder.access.setCardBackgroundColor(Color.GRAY)
            holder.desc.text="?????"
        }else holder.access.setCardBackgroundColor(Color.GREEN)
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        var tittle: TextView = item.findViewById(R.id.cardTittle)
        var desc: TextView = item.findViewById(R.id.cardDescription)
        var access:androidx.cardview.widget.CardView = item.findViewById(R.id.cardViewWork)
    }
}