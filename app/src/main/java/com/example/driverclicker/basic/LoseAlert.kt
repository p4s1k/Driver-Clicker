package com.example.driverclicker.basic

import android.app.AlertDialog
import android.content.Context
import android.os.Handler

object LoseAlert {

    private lateinit var alertDialog: AlertDialog

    fun showAlert(context: Context, presenter: PresenterBasic) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("опастность")
            .setMessage("вы вот-вот проиграете")
            .setNegativeButton("проиграть", null)
            .setPositiveButton("продолжить с просмотром видео", null)
            .setCancelable(false)
        alertDialog = builder.create()
        if (alertDialog.isShowing) {
            return
        } else {
            alertDialog.show()
        }


        val positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener() {
            positiveButton.isClickable = false
            if (presenter.tryPositive()) {
                Handler().postDelayed({ alertDialog.dismiss() }, 500)
            } else {
                Handler().postDelayed({ positiveButton.isClickable = true }, 3000)
            }
        }

        val negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        negativeButton.setOnClickListener() {
            negativeButton.isClickable = false
            Handler().postDelayed({ negativeButton.isClickable = true }, 1000)
            showSecondAlert(context, presenter)
            alertDialog.hide()
        }

    }

    private fun showSecondAlert(context: Context, presenter: PresenterBasic) {
        val secondBuilder = AlertDialog.Builder(context)
        secondBuilder.setTitle("Вы уверены, что хотите проиграть?")
            .setPositiveButton("Да") { dialog, _ ->
                presenter.restart()
                dialog.dismiss()
            }
            .setNegativeButton("Нет", null)
            .setCancelable(false)

        val secondAlertDialog = secondBuilder.create()
        if (secondAlertDialog.isShowing) {
            return
        } else secondAlertDialog.show()


        val negativeButton = secondAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        negativeButton.setOnClickListener() {
            negativeButton.isClickable = false
            alertDialog.show()
            secondAlertDialog.dismiss()
        }
    }
}