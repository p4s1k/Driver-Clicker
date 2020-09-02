package com.example.driverclicker.mainActivity

import android.content.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.driverclicker.service.MyService
import com.example.driverclicker.R
import com.example.driverclicker.repository.LocalRepository
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), View.OnClickListener,MainActivityView{
    private val BROAD = "com.example.driverclicker"   //broadcast address
    private val SAVE="save"                            //FileName SP
    private val TAGNAME="MYTAG"
    // SP KEYS
    private lateinit var pref: SharedPreferences
    private var br: BroadcastReceiver? = null  //BroadcastReceiver
    val repository = LocalRepository
    val presenter= MainActivityPresenter(repository,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startMyService()     //Start StepsUpdate Service
        Log.i(TAGNAME, "onCreate")

        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN //FULLSCREEN
        setContentView(R.layout.activity_main)  //Layout Main

        pref = getSharedPreferences(SAVE, Context.MODE_PRIVATE)  //reg SP SAVE_file
        //for clickListener
        image_car.setOnClickListener(this)
        button_restart.setOnClickListener (this)
        //наполняем View контентом
        presenter.changeProfession() //check Profession проверяет профу и
        presenter.progressCheck()   //check Progress (level)
        presenter.showMoney()
//        presenter.showText("Деньги ${presenter.loadMoney()}", R.id.text_money) //load money out of SP
        presenter.loadStats()     //load Stats out of SPe
//        var steps= presenter.loadMoveValue()    //load Steps out of SP
        presenter.showMoveValue()     //set steps to View

        /* BroadcastReceiver
        load steps out of service and set to View*/
        br = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val serviceSteps = intent?.getIntExtra("step", 0)
                if (serviceSteps != null) {
                    showProgress(serviceSteps,R.id.moves_bar)
                    if(moves_bar.progress==10){
                        val steps= presenter.loadMoveValue()
                        Log.i(TAGNAME,"Ресивер получил $steps")
//                        if(steps<250)steps+=1
                        presenter.showText("$steps",R.id.text_movesValue)
                        Log.i(TAGNAME, "Ресивер отобразил $steps")
                        showProgress(0,R.id.moves_bar)
                    }
                }
            }
        }
        // register Broadcast
        registerReceiver(br, IntentFilter(BROAD))
    }

    //CarClick logic.
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.image_car ->{
                    presenter.carClick()
                }
                R.id.button_restart ->{
                    restart()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter.changeProfession()
    }

    override fun onStop() {

        //save out of Profile to SP: Money, Profession, Lvl, Skill, Step, Stats and start Service
        Log.i(TAGNAME, "onStop")
        startMyService()
        super.onStop()
    }

    override fun onDestroy() {
        //unregister BroadCastReceiver and start Service
        unregisterReceiver(br)
        Log.i(TAGNAME, "рессивер отключен")
        Log.i(TAGNAME, "onDestroy")
        startMyService()
        super.onDestroy()

    }

    //start service method
    private fun startMyService (){
        val intentService=Intent(this, MyService::class.java)
        startService(intentService)
    }

    //Set all Profile values to default
    private fun restart (){
        pref.edit().clear().apply()
        getSharedPreferences("access", Context.MODE_PRIVATE).edit().clear().apply()
        getSharedPreferences("stats", Context.MODE_PRIVATE).edit().clear().apply()
        presenter.showMoveValue()
        presenter.changeProfession()
        presenter.loadMoveValue()
        presenter.progressCheck()
        presenter.loadStats()
        presenter.showMoney()
    }


    override fun onResume() {
        Log.i(TAGNAME, "onResume")
        startMyService()
        super.onResume()
        presenter.loadMoney()
    }

    override fun changeBackgroundMain(backgroundResource: Int) {
        main_activity.setBackgroundResource(backgroundResource)
    }

    override fun changeImageCarMain(carImageResource: Int) {
        image_car.setImageResource(carImageResource)
    }

    override fun showToast(arg: String) {
        Toast.makeText(this,arg,Toast.LENGTH_LONG).show()
    }


    override fun showText(str: String, viewId: Int) {
        val a=findViewById<TextView>(viewId)
        a.text= str
    }

    override fun showProgress(int: Int, id: Int) {
        val a = findViewById<ProgressBar>(id)
        a.progress=int
    }

    override fun startService() {
        startMyService()
    }

    override fun checkProgressMax(viewId: Int): Boolean {
        val progressBar=findViewById<ProgressBar>(viewId)
        return progressBar.progress==progressBar.max
    }

}