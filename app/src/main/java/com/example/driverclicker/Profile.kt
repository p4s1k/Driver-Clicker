package com.example.driverclicker

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class Profile(
    var money: Int,
    var profession: String,
    var income: Int,
    var lvl: Int,
    var skill: Int,
    var step: Int )  {}