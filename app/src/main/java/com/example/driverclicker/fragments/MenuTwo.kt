package com.example.driverclicker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.driverclicker.MainActivity
import com.example.driverclicker.R
import kotlinx.android.synthetic.main.menu2.*


class MenuTwo: Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu2, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        big_button2.setOnClickListener {
            (context as MainActivity).moneyPlus()
        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}