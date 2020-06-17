package com.example.driverclicker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.driverclicker.fragments.FragmentContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_action_panel.*
import kotlinx.android.synthetic.main.fragment_status_panel.*
import kotlinx.android.synthetic.main.menu.*


class MainActivity : AppCompatActivity(), View.OnClickListener{
    val SAVE="save"
    val SAVE_MONEY="money"
    lateinit var pref: SharedPreferences
    val profile = Profile()
    var money= profile.count_money

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences(SAVE, Context.MODE_PRIVATE)
        image_car.setOnClickListener(this)
        taxi2.setOnClickListener(this)
        pizza2.setOnClickListener(this)
        checkProfession()
//        FragmentContainerCrate()
    }
    fun FragmentContainerCrate(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, FragmentContainer(),"container")
            .addToBackStack(FragmentContainer::class.java.name)
            .commit()}

    private fun checkProfession(){
        if(profile.proffesion!=null) {
            when (profile.proffesion) {
                "pizza" -> {
                    image_profession_build.setImageResource(R.drawable.pizza_build)
                    main_activity.setBackgroundResource(R.drawable.background_day)
                }
                "taxi" -> {
                    image_profession_build.setImageResource(R.drawable.club_build)
                    main_activity.setBackgroundResource(R.drawable.background_dark)
                }
            }
        }
    }
    fun changeProfession(p:String){
        profile.proffesion=p
        checkProfession()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.image_car ->{moneyPlus()}
                R.id.taxi2->{changeProfession("taxi") }
                R.id.pizza2->{ changeProfession("pizza") }
//                R.id.button_1->{
//                    supportFragmentManager
//                        .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .replace(R.id.main_activity, Menu())
//                        .addToBackStack(Menu()::class.java.name)
//                        .commit()
//                }
                R.id.big_button->{moneyPlus()}
            }
        }
    }
    fun moneyPlus(){
        money=pref.getInt(SAVE_MONEY,0)
        money+=1
        text_money.text=money.toString()
        val editor=pref.edit()
        editor.putInt(SAVE_MONEY, money)
        editor.apply()
    }
    override fun onResume() {
        super.onResume()
        if (pref.contains(SAVE_MONEY)){
            money=pref.getInt(SAVE_MONEY,0)
            text_money.text=money.toString()
        }
    }

}