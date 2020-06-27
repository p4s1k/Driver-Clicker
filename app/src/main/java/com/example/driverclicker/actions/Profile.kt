package com.example.driverclicker.actions

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.driverclicker.MainActivity
import com.example.driverclicker.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_status_panel.*

class Profile(): AppCompatActivity(){
    val SAVE="save"
    val SAVE_MONEY="money"
    lateinit var pref:SharedPreferences

//    fun saveMoney(money: Int){
//        pref = getSharedPreferences(SAVE, Context.MODE_PRIVATE)
//        val editor=pref.edit()
//        editor.putInt(SAVE_MONEY, money)
//        editor.apply()
//    }
}

