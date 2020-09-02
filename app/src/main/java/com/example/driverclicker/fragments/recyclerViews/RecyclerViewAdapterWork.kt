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
import com.example.driverclicker.fragments.recyclerViews.data.WorkDataModel

//RV for MenuFour()

class RecyclerViewAdapterWork(val workList: ArrayList<WorkDataModel>) :
    RecyclerView.Adapter<RecyclerViewAdapterWork.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(View)
    }

    override fun getItemCount(): Int {
        return workList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workListElement = workList[position]
        holder.show(workListElement, position)
        //если доступно
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var tittle: TextView = item.findViewById(R.id.cardTittle)
        var desc: TextView = item.findViewById(R.id.cardDescription)
        var icon: ImageView = item.findViewById(R.id.itemImage_work)
        var access: androidx.cardview.widget.CardView = item.findViewById(R.id.cardViewWork)
        fun show(workListElement: WorkDataModel, position: Int) {

            tittle.text = workListElement.tittle
            desc.text = workListElement.desc
            icon.setImageResource(workListElement.icon)
            if (workListElement.achieved) {
                access.setCardBackgroundColor(Color.GRAY)
                return
            }    //если куплено
            if (position == 0 && !workListElement.achieved) {
                access.setCardBackgroundColor(Color.GREEN)
            }else access.setCardBackgroundColor(Color.RED)
            if (position != 0) {
                if (!workListElement.achieved && workList[position - 1].achieved) {
                    access.setCardBackgroundColor(Color.GREEN)
                }
            }
        }

    }

}