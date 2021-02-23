package com.example.driverclicker.mainActivity.fragments.actionPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.driverclicker.R
import com.example.driverclicker.fragments.FragmentContainer
import com.example.driverclicker.fragments.menuFour.MenuFourFragment
import com.example.driverclicker.fragments.menuOne.MenuOneFragment
import com.example.driverclicker.fragments.menuThree.MenuThreeFragment
import com.example.driverclicker.fragments.menuTwo.MenuTwoFragment
import com.example.driverclicker.mainActivity.MainActivity
import kotlinx.android.synthetic.main.fragment_action_panel.*

class ActionPanelFragment: Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {return inflater.inflate(R.layout.fragment_action_panel, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_1.setOnClickListener(this)
        button_2.setOnClickListener(this)
        button_3.setOnClickListener(this)
        button_4.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        if(v!=null){
            when(v.id){
                R.id.button_1->createFragment(MenuOneFragment(),"menu1tag")
                R.id.button_2->createFragment(MenuTwoFragment(),"menu2tag")
                R.id.button_3->createFragment(MenuThreeFragment(),"menu3tag")
                R.id.button_4->createFragment(MenuFourFragment(),"menu4tag")
            }
        }
    }

    private fun fragmentContainerCrate(){
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.frame_layout, FragmentContainer(),"container")?.addToBackStack(MainActivity::class.java.name)
            ?.commit()
    }

    private fun createFragment(fragment: Fragment, tag:String ){
        val ac=activity
        val sup= ac?.supportFragmentManager
        if(ac!=null){
            if(sup?.findFragmentByTag("container")==null){
                fragmentContainerCrate()
            } else if (sup.findFragmentByTag(tag)!=null) {
                sup.popBackStack()
                return
            }
            ac.supportFragmentManager
                .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_container, fragment, tag)
                .commit()
        }
    }

    //работет некорректно не очищается BackStack
//    override fun onClick(v: View?) {
//        val ac=activity
//        val sup= ac?.supportFragmentManager
//
//        //create fragment logic.
//        fun createFragment(fragment: Fragment, tag:String ){
//            if(ac!=null){
//                val current = sup?.findFragmentById(R.id.frame_layout)
//                if(current!=null){
//                    ac.supportFragmentManager.beginTransaction().remove(current).commit()
//                    ac.supportFragmentManager.beginTransaction().disallowAddToBackStack()
//                }
//                ac.supportFragmentManager.beginTransaction()
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .replace(R.id.frame_layout, fragment, tag)
////                    .addToBackStack(MainActivity::class.java.name)
//                    .addToBackStack(null)
//                    .commit()
//            } }
//
//        //crate fragment out of ClickListener
//        if(v!=null){
//            when(v.id){
//                R.id.button_1 ->createFragment(MenuOneFragment(),"menu1tag")
//                R.id.button_2 ->createFragment(MenuTwoFragment(),"menu2tag")
//                R.id.button_3 ->createFragment(MenuThreeFragment(),"menu3tag")
//                R.id.button_4 ->createFragment(MenuFourFragment(),"menu4tag")
//            }
//        }
//    }
}