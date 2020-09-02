package com.example.driverclicker.fragments.menuOne

import CustomCellClickListener
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.driverclicker.R
import com.example.driverclicker.basic.ViewBasic
import com.example.driverclicker.fragments.recyclerViews.RecyclerViewAdapterStatus
import com.example.driverclicker.fragments.recyclerViews.data.StatusDataModel
import com.example.driverclicker.repository.LocalRepository
import com.example.driverclicker.service.MyService
import kotlinx.android.synthetic.main.activity_main.*

class MenuOneFragment: Fragment(), View.OnClickListener, MenuOneView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {return inflater.inflate(R.layout.menu1, container, false)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = LocalRepository
        val presenter = MenuOnePresenter( this, repository)

        val rv:RecyclerView=view.findViewById(R.id.recyclerViewHealth)
        rv.layoutManager= LinearLayoutManager(activity)
        val healthList =presenter.initHealth()
        rv.adapter=RecyclerViewAdapterStatus(healthList)

        //ClickListener
        rv.addOnItemTouchListener(CustomCellClickListener(rv,
            object : CustomCellClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    //сделать онклик в презентере и логику трех строк ниже перенести туда
                    if(presenter.checkMovesValue()) {//по клику проверяет, есть ли ходы
                        presenter.getAFavor(position) //в случае, если ходы есть
                    }else showToast("Нет ходов") //если ходов нет
                }
            }))
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {}

    override fun showToast(arg: String) {
        Toast.makeText(activity, arg, Toast.LENGTH_LONG).show() }

    override fun showText(str: String, viewId: Int) {
        activity?.findViewById<TextView>(viewId)?.text=str
    }

    override fun showProgress(int: Int, id: Int) {
        val a = activity?.findViewById<ProgressBar>(id)
        a?.progress=int
    }

    override fun startService() {
        val intentService= Intent(activity, MyService::class.java)
        activity?.startService(intentService)
        
    }

    override fun checkProgressMax(viewId: Int): Boolean {
        val progressBar=activity?.findViewById<ProgressBar>(viewId)
        return progressBar?.progress==progressBar?.max
    }

    override fun closeFragment() {
        activity?.supportFragmentManager?.popBackStack()
//        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
    }

    override fun changeBackgroundMain(backgroundResource: Int) {
        activity?.main_activity?.setBackgroundResource(backgroundResource)
    }

    override fun changeImageCarMain(carImageResource: Int) {
        activity?.image_car?.setImageResource(carImageResource)
    }
}