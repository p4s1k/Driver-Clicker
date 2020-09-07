package com.example.driverclicker.fragments.menuThree

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
import com.example.driverclicker.fragments.recyclerViews.RecyclerViewAdapterStatus
import com.example.driverclicker.repository.LocalRepository
import com.example.driverclicker.service.MyService
import kotlinx.android.synthetic.main.activity_main.*

class MenuThreeFragment: Fragment(),MenuThreeView {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository= LocalRepository
        val presenter= MenuThreePresenter(this,repository)

        val rv: RecyclerView =view.findViewById(R.id.recyclerViewMood)
        rv.layoutManager= LinearLayoutManager(activity)

        //init Data
        val moodList = presenter.initData()
        rv.adapter= RecyclerViewAdapterStatus(moodList)

        //ClickListener
        rv.addOnItemTouchListener(CustomCellClickListener(rv,
            object : CustomCellClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    if(presenter.checkMovesValue()) {
                        presenter.getAFavor(position)
                        showToast("$position item clicked!")
                    }else showToast("Нет ходов")
                }
            }))

        super.onViewCreated(view, savedInstanceState)
    }

    override fun showToast(arg: String) {
        Toast.makeText(activity, arg, Toast.LENGTH_LONG).show()
    }

    override fun showText(str: String, viewId: Int) {
        activity?.findViewById<TextView>(viewId)?.text=str
    }

    override fun showProgress(int: Int, id: Int) {
        activity?.findViewById<ProgressBar>(id)?.progress = int
    }

    override fun startService() {
        val intentService= Intent(activity, MyService::class.java)
        activity?.startService(intentService)
    }

    override fun checkProgressMax(viewId: Int): Boolean {
        val progressBar=activity?.findViewById<ProgressBar>(viewId)
        return progressBar?.progress==progressBar?.max
    }

    override fun changeBackgroundMain(backgroundResource: Int) {
        activity?.main_activity?.setBackgroundResource(backgroundResource)
    }

    override fun changeImageCarMain(carImageResource: Int) {
        activity?.image_car?.setImageResource(carImageResource)
    }

    override fun closeFragment() {
        activity?.supportFragmentManager?.popBackStack()
    }
    override fun resetLose(id: Int) {
        activity?.findViewById<TextView>(id)?.text=""
    }
}