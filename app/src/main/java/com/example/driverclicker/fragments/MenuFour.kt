package com.example.driverclicker.fragments

import CustomCellClickListener
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.driverclicker.MainActivity
import com.example.driverclicker.R

class MenuFour: Fragment() {

    var works = ArrayList<WorkDataModel>()
    private fun initWorks(works:ArrayList<WorkDataModel>) {
        works.add(WorkDataModel("profession","newspaper", "Телега", R.drawable.newspaper, 0, 0, true, R.drawable.item_icon_newspaper))
        works.add(WorkDataModel("profession","post", "велик", R.drawable.bicycle, 5,1, false, R.drawable.item_icon_post))
        works.add(WorkDataModel("upgrade","Права","Купить права на авто за 10р",R.drawable.bicycle, 100, 0, false, R.drawable.item_icon_license))
        works.add(WorkDataModel("profession","sushi", "сивик", R.drawable.civic, 100, 3, false, R.drawable.item_icon_sushi))

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val preferences = this.activity?.getSharedPreferences("access", Context.MODE_PRIVATE)

        val rootView = inflater.inflate(R.layout.menu4, container, false)
        val rv:RecyclerView = rootView.findViewById(R.id.recyclerViewWork)
        rv.layoutManager = LinearLayoutManager(activity)

        initWorks(works)
        if(preferences!=null)for(i in 1 until works.size) works[i].access = preferences.getBoolean( works[i].tittle, false)

        rv.adapter= RecyclerViewAdapterWork(works)

        rv.addOnItemTouchListener(CustomCellClickListener(rv,
            object : CustomCellClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val cont=(context as MainActivity)
                    checkchoose(position)
                }
            }))

        return rootView



    }

    fun checkchoose(position: Int){
        val cont=(context as MainActivity)
        val preferences = this.activity?.getSharedPreferences("access", Context.MODE_PRIVATE)

        var accessText: String = "Требуется: \n"
        var accessMoney:Boolean= false
        var accessLevel:Boolean = false

        val price =works[position].price
        val level=works[position].level
        val access=works[position].access
        if(!access)accessText+="${works[position-1].tittle}\n"
        if (cont.profile.lvl>=level){ accessLevel=true} else accessText+="$level уровень\n"
        if (cont.profile.money>=price){ accessMoney=true} else accessText+="$price рублей"
        if (access && accessLevel && accessMoney){
            cont.moneyMinus(price)

            if(preferences!=null){
            val editor = preferences.edit()
            if(position+1<works.size)editor.putBoolean(works[position+1].tittle, true)
            editor.apply()
            }
            if(works[position].type=="profession") cont.changeProfession(works[position].tittle)

            Toast.makeText(activity,"Приобретено ${works[position].tittle}", Toast.LENGTH_LONG).show()
            activity?.onBackPressed()
        }else Toast.makeText(activity,accessText, Toast.LENGTH_LONG).show()
    }


    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}