package com.example.driverclicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.driverclicker.fragments.*
import kotlinx.android.synthetic.main.fragment_action_panel.*

class ActionPanelFragment: Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_action_panel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_1.setOnClickListener(this)
        button_2.setOnClickListener(this)
        button_3.setOnClickListener(this)
        button_4.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        val ac=activity
        val sup= ac?.supportFragmentManager
        val f1 = Menu()
        val f2 = MenuTwo()
        val f3 = MenuThree()
        val f4 = MenuFour()
        fun fragmentContainerCrate(){
            if (ac!=null){
                ac.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, FragmentContainer(),"container")
                    .addToBackStack(MainActivity::class.java.name)
                    .commit()}}
        fun createFragment(fragment: Fragment, tag:String ){
            if(ac!=null){
                if(sup?.findFragmentByTag("container")==null)fragmentContainerCrate()
                ac.supportFragmentManager
                    .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.fragment_container, fragment, tag)
                    .commit()
            }}
        if(v!=null){
            when(v.id){
                R.id.button_1->createFragment(f1,"menu1tag")
                R.id.button_2->createFragment(f2,"menu2tag")
                R.id.button_3->createFragment(f3,"menu3tag")
                R.id.button_4->createFragment(f4,"menu4tag")
            }
        }
        }
    }