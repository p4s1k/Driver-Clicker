package com.example.driverclicker

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_status_panel.*
import kotlinx.android.synthetic.main.menu2.*


class MainActivity : AppCompatActivity(), View.OnClickListener{
    val SAVE="save"
    val MONEY="money"
    val PROFESSION ="profession"
    val LVL ="level"
    val SKILL = "skill"
    val STEP = "step"
    lateinit var pref: SharedPreferences
    val profile = Profile(0, "pizza", 1, 0, 0, 800)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences(SAVE, Context.MODE_PRIVATE)
        image_car.setOnClickListener(this)
        taxi2.setOnClickListener(this)
        pizza2.setOnClickListener(this)
        checkProfession()
        progressCheck()
        loadMoney()

    }



    @SuppressLint("SetTextI18n")
    fun loadMoney(){
        if (pref.contains(MONEY)){
            profile.money=pref.getInt(MONEY,0)
            text_money.text="Деньги "+profile.money.toString()
        }
    }
    private fun checkProfession(){
        profile.profession= pref.getString(PROFESSION, "a").toString()
        when (profile.profession) {
            "pizza" -> {
                image_profession_build.setImageResource(R.drawable.pizza_build)
                main_activity.setBackgroundResource(R.drawable.background_day)
                profile.income=1


            }
            "taxi" -> {
                image_profession_build.setImageResource(R.drawable.club_build)
                main_activity.setBackgroundResource(R.drawable.background_dark)
                profile.income=2
            }
        }
    }
    fun changeProfession(p:String){
        profile.profession=p
        val editor=pref.edit()
        editor.putString(PROFESSION, p)
        editor.apply()
        checkProfession()

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.image_car ->{
                    moneyPlus()
                    progressUp()}
                R.id.taxi2->changeProfession("taxi")
                R.id.pizza2->changeProfession("pizza")
                R.id.big_button2->moneyPlus()
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun moneyPlus(){
        profile.money=pref.getInt(MONEY,0)
        profile.money+=profile.income
        text_money.text="Деньги "+profile.money.toString()
        val editor=pref.edit()
        editor.putInt(MONEY, profile.money)
        editor.apply()
    }
    @SuppressLint("SetTextI18n")

    fun progressUp(){
        var pb=(findViewById<ProgressBar>(R.id.experience_bar))
        var lvl =(findViewById<TextView>(R.id.text_lvl))
        val editor=pref.edit()
        profile.skill+= profile.step
        pb.progress = profile.skill
        if (pb.progress==pb.max){
            profile.lvl += 1
            if(profile.step>250)profile.step -=10
            lvl.text="Ур. "+profile.lvl.toString()
            pb.progress=0
            profile.skill=0
        }
        editor.putInt(LVL, profile.lvl)
        editor.putInt(SKILL, profile.skill)
        editor.putInt(STEP, profile.step)
        editor.apply()

    }
    fun progressCheck(){
        var pb=(findViewById<ProgressBar>(R.id.experience_bar))
        var lvl =(findViewById<TextView>(R.id.text_lvl))
        profile.lvl=pref.getInt(LVL, 0)
        profile.skill=pref.getInt(SKILL, 0)
        profile.step=pref.getInt(STEP, 800)
        pb.progress=profile.skill
        lvl.text="Ур. "+profile.lvl
    }

    override fun onStop() {
        val editor=pref.edit()
        editor.putInt(MONEY, profile.money)
        editor.apply()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onResume() {
        super.onResume()
        loadMoney()
    }

}