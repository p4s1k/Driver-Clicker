package com.example.driverclicker.fragments.menuTwo

import CustomCellClickListener
import android.app.ActivityManager
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.driverclicker.R
import com.example.driverclicker.fragments.recyclerViews.RecyclerViewAdapterStatus
import com.example.driverclicker.basic.LoseAlert
import com.example.driverclicker.repository.Ads
import com.example.driverclicker.repository.LocalRepository
import com.example.driverclicker.service.MyService
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import kotlinx.android.synthetic.main.activity_main.*

class MenuTwoFragment: Fragment(), View.OnClickListener, MenuTwoView {
    val repository= LocalRepository
    val presenter= MenuTwoPresenter(this, repository)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_menu2, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val rv: RecyclerView =view.findViewById(R.id.recyclerViewHunger)
        rv.layoutManager= LinearLayoutManager(activity)

        //init Data
        val hungerList=presenter.initData()
        rv.adapter= RecyclerViewAdapterStatus(hungerList)

        //ClickListener
        rv.addOnItemTouchListener(CustomCellClickListener(rv,
            object : CustomCellClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    if(presenter.checkMovesValue()) {
                        presenter.getAFavor(position)
                    }else showToast("Нет ходов")
                }
            }))

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
    }

    override fun showToast(strRes: Int) {
        Toast.makeText(activity, activity?.getString(strRes), Toast.LENGTH_LONG).show()
    }

    override fun showToast(arg: String) {
        Toast.makeText(activity,arg,Toast.LENGTH_LONG).show()
    }

    override fun showToast(strResList: List<Int>) {
        TODO("Not yet implemented")
    }

    override fun showToast(strResMap: Map<String, Int>) {
        TODO("Not yet implemented")
    }

    override fun showText(str: String, viewId: Int) {
        activity?.findViewById<TextView>(viewId)?.text=str
    }

    override fun showProgress(int: Int, id: Int) {
        activity?.findViewById<ProgressBar>(id)?.progress=int
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = activity?.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    override fun startService() {
        if (!isMyServiceRunning(MyService::class.java)) {
            val intentService= Intent(activity, MyService::class.java)
            activity?.startService(intentService)
        }
    }

    override fun stopService() {
        val intentService = Intent(activity, MyService::class.java)
        activity?.stopService(intentService)
    }

    override fun showLoseRewardedAd(callback: RewardedAdCallback) {
        Ads.loseRewardedAd.show(requireActivity(), callback)
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

    override fun isNetworkAvailable(): Boolean {
        return requireActivity().isConnectedToNetwork()
    }

    private fun FragmentActivity.isConnectedToNetwork(): Boolean {
        val connectivityManager =
            this.getSystemService(FragmentActivity.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

    override fun showAlert() {
        LoseAlert.showAlert(requireActivity(), presenter)
    }

    override fun isMoveAdVisibility(): Boolean {
        return requireActivity().moves_ad_image.visibility != View.GONE
    }

    override fun changeMoveAdImageVisibility(boolean: Boolean) {
        requireActivity().moves_ad_image.visibility=if (boolean){
            View.VISIBLE
        }else{
            View.GONE
        }
    }
}