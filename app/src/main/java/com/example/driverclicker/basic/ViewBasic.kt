package com.example.driverclicker.basic

import android.content.Context

interface ViewBasic: LoseRewardedAd {
    fun isMoveAdVisibility(): Boolean{return true}
    fun isNetworkAvailable(): Boolean
    fun showAlert()
    fun showToast(strRes: Int)
    fun showToast(str: String)
    fun showToast(strResList: List<Int>)
    fun showToast(strResMap: Map<String, Int>)
    fun showText(str: String, viewId: Int)
    fun showProgress(int: Int, id: Int)
    fun startService()
    fun checkProgressMax(viewId: Int): Boolean
    fun closeFragment()
    fun changeBackgroundMain(backgroundResource: Int)
    fun changeImageCarMain(carImageResource: Int)
    fun resetLose(id: Int)
    fun stopService()
    fun startAlert(){}
    fun changeMoveAdImageVisibility(boolean: Boolean){}
}