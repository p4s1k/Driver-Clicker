package com.example.driverclicker.fragments

import CustomCellClickListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.driverclicker.MainActivity
import com.example.driverclicker.R

class MenuThree: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.menu3, container, false)
        val rv: RecyclerView =rootView.findViewById(R.id.recyclerViewMood)
        val preferences = this.activity
            ?.getSharedPreferences("save", Context.MODE_PRIVATE)
        rv.layoutManager= LinearLayoutManager(activity)
        var moodlist = ArrayList<StatusDataModel>()
        for(i in 1..50){
            moodlist.add(StatusDataModel("Настроение $i", "Описание $i", 10,-5))
        }
        rv.adapter= RecyclerViewAdapterStatus(moodlist)

        rv.addOnItemTouchListener(CustomCellClickListener(rv,
            object : CustomCellClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val a= preferences?.getInt("servicestep", 5)
                    val cont = (context as MainActivity)
                    if(a!=0) {
                        cont.step()
                        cont.setStats(1, moodlist[position].plus, "mood")
                        cont.setStats(moodlist[position].minus, 0, "hunger")
                        cont.setStats(moodlist[position].minus, 0, "health")
                        Toast.makeText(activity, "$position item clicked!", Toast.LENGTH_LONG).show()
                    }else Toast.makeText(activity,"Нет ходов", Toast.LENGTH_LONG).show()
                }
            }))

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val cont = (context as MainActivity)
//        big_button3.setOnClickListener{
//            cont.setStats(-5,0, "health")
//            cont.setStats(-5,0, "hunger")
//            cont.setStats(1,10, "mood")
//    }
    }
}