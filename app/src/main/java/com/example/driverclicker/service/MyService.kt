package com.example.driverclicker.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import com.example.driverclicker.repository.LocalRepository
import java.util.*


class MyService : Service() {

    lateinit var pref: SharedPreferences
    lateinit var timer: CountDownTimer

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("MYTAG", "Сервис работает")
//        return super.onStartCommand(intent, flags, startId)
        val progress = intent?.getIntExtra("progress", 0)
        check(progress)
        return START_NOT_STICKY
    }

    fun check(progress: Int? = 0) {
        if (pref.getInt("servicestep", 250) < 250) {
            reload(progress)
        } else stopSelf()
    }


    private fun reload(intentProgress: Int?) {
        var progress = 0
        if (intentProgress != null) progress = intentProgress
        val b = "com.example.driverclicker"
        val intent = Intent(b)

        timer = object : CountDownTimer(10100, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("MYTAG", Thread.currentThread().name)
                Log.i("MYTAG", "Таймер $millisUntilFinished")
                progress += 1
                intent.putExtra("step", progress)
                sendBroadcast(intent)
                if (progress == 10) {
                    var step = pref.getInt("servicestep", 249)
                    step += 1
                    pref.edit().putInt("servicestep", step).commit()
                    Log.i("MYTAG", "Таймер сохранился $step")
                    Log.i("MYTAG", "Progress if= $progress")
                }
                if(progress<10)pref.edit().putInt("serviceProgress", progress).commit()
                Log.i("MYTAG", "Progress после if= $progress")
                if (progress > 10) {
                    cancel()
                    onFinish()
                    sendBroadcast(Intent("com.example.driverclicker").putExtra("step", 0))
                }
            }

            override fun onFinish() {
                intent.putExtra("step", 0)
                sendBroadcast(intent)

                check()

                Log.i("MYTAG", "Таймер закончился")
            }
        }
        timer.start()
    }

    override fun onCreate() {
        super.onCreate()
        pref = getSharedPreferences("save", Context.MODE_PRIVATE)
//        check()

        Log.i("MYTAG", "Сервис запущен")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MYTAG", "Сервис отключен")
        if (this::timer.isInitialized) {
            LocalRepository.saveLong("save", "date", Date().time)
//            pref.edit().putLong("date", Date().time).apply()
            timer.cancel()
        }
        sendBroadcast(Intent("com.example.driverclicker").putExtra("step", 0))
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        Log.i("MYTAG", "OnTask")
        if (this::timer.isInitialized) {
            timer.cancel()
            LocalRepository.saveLong("save", "date", Date().time)
//            pref.edit().putLong("date", Date().time).apply()
            sendBroadcast(Intent("com.example.driverclicker").putExtra("step", 0))
        }
        super.onTaskRemoved(rootIntent)
    }


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }


}
