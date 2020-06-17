package com.example.driverclicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.driverclicker.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_action_panel.*
import kotlinx.android.synthetic.main.fragment_container.*

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
//        val ac=activity
        button_1.setOnClickListener(this)
        button_2.setOnClickListener(this)
        button_3.setOnClickListener(this)
        button_4.setOnClickListener(this)

//        button_1.setOnClickListener{
//            if (ac != null) {
//                ac.supportFragmentManager
//                    .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .replace(R.id.frame_layout, Menu())
//                    .addToBackStack(MainActivity::class.java.name)
//                    .commit()
//                        }
//        }
//        button_2.setOnClickListener{
//            if (ac != null) {
//                ac.supportFragmentManager
//                    .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .replace(R.id.frame_layout, MenuTwo())
//                    .addToBackStack(MainActivity::class.java.name)
//                    .commit()
//            }
//        }
    }


    override fun onClick(v: View?) {
        val ac=activity
        val sup= ac?.supportFragmentManager
        val f1 = Menu()
        val f2 = MenuTwo()
        val f3 = MenuThree()
        val f4 = MenuFour()
        fun FragmentContainerCrate(){
            if (ac!=null){
                ac.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, FragmentContainer(),"container")
//                .addToBackStack(FragmentContainer::class.java.name)
                    .addToBackStack(MainActivity::class.java.name)
                .commit()}}
        if(v!=null){
            when(v.id){
                R.id.button_1->{ if (ac != null) {
                    if(sup?.findFragmentByTag("container")==null)FragmentContainerCrate()
//                    if(sup?.findFragmentByTag("menutag")==null){
                    ac.supportFragmentManager
                        .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.fragment_container, f1, "menu1tag")
//                        .addToBackStack(f1::class.java.name)
                        .commit()
//                    }
                }
                }
                R.id.button_2->{if (ac!=null){
                    if(sup?.findFragmentByTag("container")==null)FragmentContainerCrate()
                    ac.supportFragmentManager
                        .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.fragment_container, f2, "menu2tag")
//                        .addToBackStack(f1::class.java.name)
                        .commit()
                }}
                R.id.button_3->{if (ac!=null){
                    if(sup?.findFragmentByTag("container")==null)FragmentContainerCrate()
                    ac.supportFragmentManager
                        .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.fragment_container, f3, "menu3tag")
//                        .addToBackStack(f1::class.java.name)
                        .commit()
                }}
                R.id.button_4->{if (ac!=null){
                    if(sup?.findFragmentByTag("container")==null)FragmentContainerCrate()
                    ac.supportFragmentManager
                        .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.fragment_container, f4, "menu4tag")
//                        .addToBackStack(f1::class.java.name)
                        .commit()
                }}

            }
        }
    }
}