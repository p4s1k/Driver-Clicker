package com.example.driverclicker.mainActivity

import android.app.ActivityManager
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.driverclicker.R
import com.example.driverclicker.basic.LoseAlert
import com.example.driverclicker.repository.Ads
import com.example.driverclicker.repository.LocalRepository
import com.example.driverclicker.service.MyService
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener, MainActivityView {
    private val BROAD = "com.example.driverclicker"   //broadcast address
    private val TAGNAME = "MYTAG"
    private var back_pressed: Long = 0
    private lateinit var exitToast: Toast
    //    private lateinit var builder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog

    // SP KEYS
    private var br: BroadcastReceiver? = null  //BroadcastReceiver
    private val repository = LocalRepository
    private val presenter = MainActivityPresenter(repository, this)


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        startService(presenter.data())     //Start StepsUpdate Service

        Log.i(TAGNAME, "onCreate")

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN //FULLSCREEN
        setContentView(R.layout.activity_main)  //Layout Main

        image_car.setOnClickListener(this)
        button_restart.setOnClickListener(this)
        moves_ad_image.setOnClickListener(this)
//        builder = AlertDialog.Builder(this)


        //наполняем View контентом
        presenter.start()

        br = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val serviceSteps = intent?.getIntExtra("step", 0)
                if (serviceSteps != null) {
                    showProgress(serviceSteps, R.id.moves_bar)

                    if (serviceSteps == 10) {
                        val steps = presenter.loadMoveValue()
                        Log.i(TAGNAME, "Ресивер получил $steps")
                        presenter.showText("$steps", R.id.text_movesValue)
                        Log.i(TAGNAME, "Ресивер отобразил $steps")
                    }
                }
            }
        }
        registerReceiver(br, IntentFilter(BROAD))
        Log.i(TAGNAME, "рессивер включен")

        exitToast = Toast.makeText(this, getString(R.string.exit_toast_text), Toast.LENGTH_SHORT)

    }


    //CarClick logic.
    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.image_car -> {
                    v.isClickable = false
                    presenter.carClick()
                    Handler().postDelayed({
                        v.isClickable = true
                    }, 300)
//                    checkLoadRewardedAd(moneyRewardedAd)
                }
                R.id.button_restart -> {
                    presenter.restart()
                }
                R.id.moves_ad_image -> {
                    presenter.moveImageClick()
                }
            }
        }
    }

    override fun showMoveAlert() {
        MoveAlert.showAlert(this, presenter)
    }

    override fun isMoveAdVisibility(): Boolean {
        return moves_ad_image.visibility != View.GONE
    }

    override fun showMoveRewardedAd(callback: RewardedAdCallback) {
        Ads.movesRewardedAd.show(this, callback)
    }

    override fun hideMoveAdImage() {
        moves_ad_image.visibility = View.GONE
    }

    override fun changeMoveAdImageVisibility(boolean: Boolean) {
        moves_ad_image.visibility =
            if (boolean) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    override fun showMoneyRewardedAd(callback: RewardedAdCallback) {
        Ads.moneyRewardedAd.show(this, callback)
    }

    override fun showAlert() {
//        Alert(this, presenter).showAlert()
        LoseAlert.showAlert(this, presenter)
    }

    override fun startAlert() {
        alertDialog.show()
    }

    override fun showLoseRewardedAd(callback: RewardedAdCallback) {
        Ads.loseRewardedAd.show(this, callback)
    }

    override fun showToast(strRes: Int) {
        Toast.makeText(this, getString(strRes), Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.findFragmentByTag("container")!=null){
            super.onBackPressed()
            return
        }
        if(back_pressed+2000 > System.currentTimeMillis()){
            super.onBackPressed()
            exitToast.cancel()
        } else{
            exitToast.show()
            back_pressed = System.currentTimeMillis()
        }
    }

    override fun isNetworkAvailable(): Boolean {
        return isConnectedToNetwork()
    }

    private fun Context.isConnectedToNetwork(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

    override fun onPause() {
        Log.i(TAGNAME, "onPause")
        stopService()
        super.onPause()
    }

    override fun onStop() {
        //save out of Profile to SP: Money, Profession, Lvl, Skill, Step, Stats and start Service
        Log.i(TAGNAME, "onStop")
        stopService()
        super.onStop()
    }

    override fun onDestroy() {
        //unregister BroadCastReceiver and start Service
        Log.i(TAGNAME, "onDestroy")
        unregisterReceiver(br)
        Log.i(TAGNAME, "рессивер отключен")

        stopService()

        super.onDestroy()


    }


    override fun onResume() {
        Log.i(TAGNAME, "onResume")
        startService(presenter.data())
        presenter.showMoveValue()
        super.onResume()
        presenter.loadMoney()
    }

    override fun changeBackgroundMain(backgroundResource: Int) {
        main_activity.setBackgroundResource(backgroundResource)
    }

    override fun changeImageCarMain(carImageResource: Int) {
        image_car.setImageResource(carImageResource)
    }

    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }

    override fun showToast(strResList: List<Int>) {
        TODO("Not yet implemented")
    }

    override fun showToast(strResMap: Map<String, Int>) {
        TODO("Not yet implemented")
    }


    override fun showText(str: String, viewId: Int) {
        val a = findViewById<TextView>(viewId)
        a.text = str
    }

    override fun showProgress(int: Int, id: Int) {
        val a = findViewById<ProgressBar>(id)
        a.progress = int
    }

    override fun startService() {
        if (!isMyServiceRunning(MyService::class.java)) {
            val intentService = Intent(this, MyService::class.java)
            startService(intentService)
        }
    }

    private fun startService(moveStep: Int) {
        if (!isMyServiceRunning(MyService::class.java)) {
            val intentService = Intent(this, MyService::class.java)
            intentService.putExtra("progress", moveStep)
            startService(intentService)
        }
    }

    override fun checkProgressMax(viewId: Int): Boolean {
        val progressBar = findViewById<ProgressBar>(viewId)
        return progressBar.progress == progressBar.max
    }

    override fun closeFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun resetLose(id: Int) {
        findViewById<TextView>(id).text = ""
    }

    override fun stopService() {
        val intentService = Intent(this, MyService::class.java)
        stopService(intentService)
    }
}