package com.example.driverclicker.fragments.menuFour

import CustomCellClickListener
import android.annotation.SuppressLint
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.driverclicker.R
import com.example.driverclicker.fragments.recyclerViews.RecyclerViewAdapterWork
import com.example.driverclicker.basic.LoseAlert
import com.example.driverclicker.mainActivity.MainActivity
import com.example.driverclicker.repository.Ads
import com.example.driverclicker.repository.LocalRepository
import com.example.driverclicker.service.MyService
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import kotlinx.android.synthetic.main.activity_main.*

class MenuFourFragment : Fragment(), MenuFourView {


    val repository = LocalRepository
    val presenter = MenuFourPresenter(this, repository)
    lateinit var mInterstitialAd: InterstitialAd

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

        val rv: RecyclerView = view.findViewById(R.id.recyclerViewWork)
        rv.layoutManager = LinearLayoutManager(activity)


        val worksList = presenter.initData()

        rv.adapter = RecyclerViewAdapterWork(worksList)

        rv.addOnItemTouchListener(
            CustomCellClickListener(rv,
                object : CustomCellClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        presenter.checkChoose(position)
                    }
                })
        )

    }

    override fun close() {
//        activity?.onBackPressed()

//        if (mInterstitialAd.isLoaded){
//            mInterstitialAd.show()
//        } else{
//            Log.d("ADTAG", "The interstitial wasn't loaded yet.")
//        }

        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showToast(strRes: Int) {
        Toast.makeText(activity, activity?.getString(strRes), Toast.LENGTH_LONG).show()
    }

    override fun showToast(str: String) {
        TODO("Not yet implemented")
    }

    override fun showToast(strResList: List<Int>) {
        var str = ""
        for (element in strResList.indices) {
            str += if (element == 0) {
                activity?.getString(strResList[element])
            } else "\n" + activity?.getString(strResList[element])
        }
        Toast.makeText(activity, str, Toast.LENGTH_LONG).show()
    }

    override fun showToast(strResMap: Map<String, Int>) {
        var str = ""
        strResMap.forEach {
            if (it.key == "3" || it.key == "5") {
                str += "\n" + it.value.toString() + " "
            } else {
                str += activity?.getString(it.value)
            }
        }
        Toast.makeText(activity, str, Toast.LENGTH_LONG).show()
    }

    override fun showText(str: String, viewId: Int) {
        activity?.findViewById<TextView>(viewId)?.text = str
    }

    override fun showProgress(int: Int, id: Int) {
        activity?.findViewById<ProgressBar>(id)?.progress = int
    }

    override fun startService() {
        val a = MainActivity()
    }

    override fun checkProgressMax(viewId: Int): Boolean {
        val progressBar = activity?.findViewById<ProgressBar>(viewId)
        return progressBar?.progress == progressBar?.max
    }

    override fun closeFragment() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun changeBackgroundMain(backgroundResource: Int) {
        activity?.main_activity?.setBackgroundResource(backgroundResource)
    }

    override fun changeImageCarMain(carImageResource: Int) {
        activity?.image_car?.setImageResource(carImageResource)
    }

    override fun resetLose(id: Int) {
    }

    override fun stopService() {
        val intentService = Intent(activity, MyService::class.java)
        activity?.stopService(intentService)
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

    override fun showLoseRewardedAd(callback: RewardedAdCallback) {
        Ads.loseRewardedAd.show(requireActivity(), callback)
    }

}