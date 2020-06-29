package com.example.driverclicker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.driverclicker.MainActivity
import com.example.driverclicker.R
import kotlinx.android.synthetic.main.menu.*
import kotlinx.android.synthetic.main.menu3.*

class MenuThree: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cont = (context as MainActivity)
        big_button3.setOnClickListener{
            cont.setStats(-5,0, "health")
            cont.setStats(-5,0, "hunger")
            cont.setStats(1,10, "mood")
    }}
}