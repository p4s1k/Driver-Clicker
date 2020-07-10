package com.example.driverclicker.fragments

import CustomCellClickListener
import android.annotation.SuppressLint
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

class MenuFour: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.menu4, container, false)
        val rootView = inflater.inflate(R.layout.menu4, container, false)
        val rv:RecyclerView = rootView.findViewById(R.id.recyclerViewWork)
        rv.layoutManager = LinearLayoutManager(activity)
        var works = ArrayList<WorkDataModel>()
        for(i in 1..50){
            works.add(WorkDataModel("Название $i", "Описание $i"))
        }
        rv.adapter= RecyclerViewAdapterWork(works)

        rv.addOnItemTouchListener(CustomCellClickListener(rv,
            object : CustomCellClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val cont=(context as MainActivity)
                    Toast.makeText(activity, "$position item clicked!", Toast.LENGTH_LONG).show()
                }
            }))

        return rootView
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}