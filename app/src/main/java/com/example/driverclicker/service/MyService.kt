package com.example.driverclicker.service

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*


class MyService : Service() {
    lateinit var pref: SharedPreferences
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("MYTAG", "Сервис работает")
        return super.onStartCommand(intent, flags, startId)
    }

    fun check(){
        if(pref.getInt("servicestep",250)<250){
            reload()
        }else stopSelf()
    }

    private fun reload(){
        var progress: Int =0
        val b="com.example.driverclicker"
        val intent = Intent(b)
        object : CountDownTimer(10100, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("MYTAG", "Таймер $millisUntilFinished")
                progress += 1
                intent.putExtra("step", progress)
                sendBroadcast(intent)
                if(progress==10){
                var a = pref.getInt("servicestep", 249)
                a+=1
                val editor = pref.edit()
                editor.putInt("servicestep", a)
                editor.apply()
                    Log.i("MYTAG", "Таймер сохранился $a")
                    Log.i("MYTAG", "Progress if= $progress")
                }
                Log.i("MYTAG", "Progress после if= $progress")

            }

            override fun onFinish() {
                check()
                Log.i("MYTAG", "Таймер закончился")
            }
        }.start()
    }

    override fun onCreate() {
        super.onCreate()
        pref = getSharedPreferences("save", Context.MODE_PRIVATE)
        check()
        Log.i("MYTAG", "Сервис запущен")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MYTAG", "Сервис отключен")
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }


}
