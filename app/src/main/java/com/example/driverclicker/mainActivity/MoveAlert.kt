package com.example.driverclicker.mainActivity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.enums.Progress

object MoveAlert {

    private lateinit var alertDialog: AlertDialog

    fun showAlert(context: Context, presenter: MainActivityPresenter){
        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setTitle("Награда за просмотр видео")
            .setMessage("Полчите ${Progress.ServiceAdReward.value} ходов за просмотр видео")
            .setPositiveButton("посмотреть видео", DialogInterface.OnClickListener { dialog, which ->
                presenter.showMoveRewardedAd()
            })
        alertDialog = alertBuilder.create()
        if (alertDialog.isShowing){
            return
        } else{
            alertDialog.show()
        }
    }
}