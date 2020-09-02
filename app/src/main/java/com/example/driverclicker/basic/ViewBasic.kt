package com.example.driverclicker.basic

interface ViewBasic {
    fun showToast(arg: String)
    fun showText(str:String, viewId: Int)
    fun showProgress(int: Int, id: Int)
    fun startService()
    fun checkProgressMax(viewId: Int):Boolean
    fun closeFragment(){}
    fun changeBackgroundMain(backgroundResource: Int)
    fun changeImageCarMain(carImageResource: Int)
}