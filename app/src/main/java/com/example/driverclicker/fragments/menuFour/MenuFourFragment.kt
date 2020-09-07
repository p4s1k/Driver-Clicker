package com.example.driverclicker.fragments.menuFour

import CustomCellClickListener
import android.annotation.SuppressLint
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
import com.example.driverclicker.fragments.recyclerViews.RecyclerViewAdapterWork
import com.example.driverclicker.repository.LocalRepository
import kotlinx.android.synthetic.main.activity_main.*

class MenuFourFragment: Fragment(), MenuFourView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu4, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv:RecyclerView = view.findViewById(R.id.recyclerViewWork)
        rv.layoutManager = LinearLayoutManager(activity)

        val repository=LocalRepository
        val presenter=MenuFourPresenter(this,repository)
        val worksList = presenter.initData()

        rv.adapter=
            RecyclerViewAdapterWork(
                worksList
            )

        rv.addOnItemTouchListener(CustomCellClickListener(rv,
            object : CustomCellClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    presenter.checkChoose(position)
                }
            }))

    }

    override fun close() {
//        activity?.onBackPressed()
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showToast(arg: String) {
        Toast.makeText(activity, arg, Toast.LENGTH_LONG).show()
    }

    override fun showText(str: String, viewId: Int) {
        activity?.findViewById<TextView>(viewId)?.text=str
    }

    override fun showProgress(int: Int, id: Int) {
        activity?.findViewById<ProgressBar>(id)?.progress=int
    }

    override fun startService() {
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

    override fun resetLose(id: Int) {
    }

}