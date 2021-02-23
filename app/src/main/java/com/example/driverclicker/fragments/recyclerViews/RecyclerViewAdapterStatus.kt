package com.example.driverclicker.fragments.recyclerViews

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.driverclicker.R
import com.example.driverclicker.fragments.recyclerViews.data.StatusDataModel

//RV for MenuOne/Two/Three()
class RecyclerViewAdapterStatus(private val statusList: MutableList<StatusDataModel>) :
    RecyclerView.Adapter<RecyclerViewAdapterStatus.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return statusList.size
    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val status: StatusDataModel = statusList[position]

        holder.show(status, position)
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var tittle: TextView = item.findViewById(R.id.cardTittle)
        var desc: TextView = item.findViewById(R.id.cardDescription)
        var access: androidx.cardview.widget.CardView = item.findViewById(R.id.cardViewWork)
        val icon: ImageView = item.findViewById(R.id.itemImage_work)
        fun show(status: StatusDataModel, position: Int) {
            icon.setImageResource(status.icon)
            tittle.text = itemView.context.getString(status.tittle)
            desc.text = status.desc

            if (!status.achieved) {
                access.setCardBackgroundColor(Color.GRAY)
                tittle.text = "?????"
                desc.text = "?????"
            } else access.setCardBackgroundColor(Color.GREEN)
        }
    }
}