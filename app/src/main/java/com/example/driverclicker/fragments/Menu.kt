package com.example.driverclicker.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.driverclicker.R
import kotlinx.android.synthetic.main.menu.*


class Menu: Fragment(), View.OnClickListener{
    lateinit var pref: SharedPreferences

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var ac=activity
        var button = ac?.findViewById<Button>(R.id.button_1)
//        button?.isClickable=false
        if(ac!=null) pref = ac.getSharedPreferences("save", Context.MODE_PRIVATE)
        var money = pref.getInt("money", 0)
        var textView = ac?.findViewById<TextView>(R.id.text_money)
        big_button.setOnClickListener {
            money++
            textView?.text=money.toString()
            val editor=pref.edit()
            editor.putInt("money", money)
            editor.apply()
        }
    }

    override fun onClick(v: View?) {

        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        var ac = activity
        var button = ac?.findViewById<Button>(R.id.button_1)
//        button?.isClickable=true
    }

}