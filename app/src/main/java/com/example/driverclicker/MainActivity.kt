package com.example.driverclicker

import android.annotation.SuppressLint
import android.content.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_status_panel.*


class MainActivity : AppCompatActivity(), View.OnClickListener{
    val BROAD = "com.example.driverclicker"
    val SAVE="save"
    val MONEY="money"
    val PROFESSION ="profession"
    val LVL ="level"
    val SKILL = "skill"
    val STEP = "step"
    val HEALTH = "health"
    val HUNGER = "hunger"
    val MOOD = "mood"
    val SERVICESTEP = "servicestep"
    lateinit var pref: SharedPreferences

    val profile = Profile(0, "newspaper", 1, 0, 0, 800, 100, 100, 100, 250 )

    var br: BroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startMyService()
        Log.i("MYTAG", "onCreate")
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences(SAVE, Context.MODE_PRIVATE)
        image_car.setOnClickListener(this)
        checkProfession()
        progressCheck()
        loadMoney()
        loadStats()
        loadSteps()
        setStep()

        br = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val serviceSteps = intent?.getIntExtra("step", 0)
                if (serviceSteps != null) {
                    steps_bar.progress=serviceSteps
                    if(steps_bar.progress==10){
//                        loadSteps()
                        profile.steps+=1
//                        val editor=pref.edit()
//                        editor.putInt(SERVICESTEP, profile.steps)
//                        editor.apply()
                        setStep()
                        steps_bar.progress=0
                    }
                }
            }
        }
        val infilter = IntentFilter(BROAD)
        registerReceiver(br, infilter)
    }

    private fun loadStats() {
        profile.health=pref.getInt(HEALTH, 100)
        profile.hunger=pref.getInt(HUNGER, 100)
        profile.mood=pref.getInt(MOOD, 100)
        health_bar.progress = profile.health
        hunger_bar.progress = profile.hunger
        mood_bar.progress = profile.mood
    }
    private fun saveStats(){
        val editor=pref.edit()
        editor.putInt(HEALTH, profile.health)
        editor.putInt(HUNGER, profile.hunger)
        editor.putInt(MOOD, profile.mood)
        editor.apply()
    }
     fun setStats(a: Int, b:Int, string: String){
         val c=(a..b).random()
        when(string){
            HEALTH -> {
                profile.health+=c
                if(profile.health<0) profile.health=0
                if(profile.health>100) profile.health=100
                health_bar.progress=profile.health}
            HUNGER -> {
                profile.hunger=profile.hunger+c
                if(profile.hunger<0) profile.hunger=0
                if(profile.hunger>100) profile.hunger=100
                hunger_bar.progress=profile.hunger}
            MOOD -> {
                profile.mood=profile.mood+c
                if(profile.mood<0) profile.mood=0
                if(profile.mood>100) profile.mood=100
                mood_bar.progress=profile.mood}
        }
    }

    fun loadSteps(){profile.steps = pref.getInt(SERVICESTEP,250)}
    fun setStep(){text_step.text=profile.steps.toString()
    Log.i("MYTAG", "Отрисовывает ${profile.steps}")}

    fun step(){
        if (profile.steps>0){
            Log.i("MYTAG", "клик, шаг отнимется и сохранится ${profile.steps}")
            profile.steps-= 1
            val editor=pref.edit()
            editor.putInt(SERVICESTEP, profile.steps)
            editor.apply()
            setStep()

        }
        Log.i("MYTAG", "клик, шаг отнялcя и сохранился ${profile.steps}")
        startMyService()
    }


    @SuppressLint("SetTextI18n")
    fun loadMoney(){
        if (pref.contains(MONEY)){
            profile.money=pref.getInt(MONEY,0)
            text_money.text="Деньги "+profile.money.toString()
        }
    }
    private fun checkProfession(){
        profile.profession= pref.getString(PROFESSION, "newspaper").toString()
        when (profile.profession) {
            "newspaper"->{
                image_car.setImageResource(R.drawable.newspaper)
                profile.income=1
            }
            "post"->{
                image_car.setImageResource(R.drawable.bicycle)
                profile.income=2
            }
            "sushi"->{
                image_car.setImageResource(R.drawable.civic)
                profile.income=4
            }
            "pizza" -> {
                image_car.setImageResource(R.drawable.bicycle)
                profile.income=5


            }
            "taxi" -> {
                image_profession_build.setImageResource(R.drawable.club_build)
                main_activity.setBackgroundResource(R.drawable.background_dark)
                profile.income=6
            }

        }
    }
    fun changeProfession(p:String){
        profile.profession=p
        val editor=pref.edit()
        editor.putString(PROFESSION, p)
        editor.apply()
        checkProfession()

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.image_car ->{
                    if(profile.steps>0){
                        step()
                        moneyPlus()
                        progressUp()
                        setStats(-10, 0, HEALTH)
                        setStats(-10, 0, HUNGER)
                        setStats(-10, 0,MOOD)
                    }else {step()
                        Toast.makeText(this,"Нет ходов", Toast.LENGTH_LONG).show()}
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun moneyPlus(){
//        profile.money=pref.getInt(MONEY,0)
        profile.money+=profile.income
        text_money.text="Деньги "+profile.money.toString()
//        val editor=pref.edit()
//        editor.putInt(MONEY, profile.money)
//        editor.apply()
    }
    fun moneyMinus(count:Int){
        profile.money-=count
        text_money.text="Деньги "+profile.money.toString()
    }
    @SuppressLint("SetTextI18n")

    fun progressUp(){
        var pb=(findViewById<ProgressBar>(R.id.experience_bar))
        var lvl =(findViewById<TextView>(R.id.text_lvl))
        val editor=pref.edit()
        profile.skill+= profile.step
        pb.progress = profile.skill
        if (pb.progress==pb.max){
            profile.lvl += 1
            if(profile.step>250)profile.step -=10
            lvl.text="Ур. "+profile.lvl.toString()
            pb.progress=0
            profile.skill=0
        }
        editor.putInt(LVL, profile.lvl)
        editor.putInt(SKILL, profile.skill)
        editor.putInt(STEP, profile.step)
        editor.apply()

    }
    fun progressCheck(){
        var pb=(findViewById<ProgressBar>(R.id.experience_bar))
        var lvl =(findViewById<TextView>(R.id.text_lvl))
        profile.lvl=pref.getInt(LVL, 0)
        profile.skill=pref.getInt(SKILL, 0)
        profile.step=pref.getInt(STEP, 800)
        pb.progress=profile.skill
        lvl.text="Ур. "+profile.lvl
    }

    override fun onStop() {
        val editor=pref.edit()

        editor.putInt(MONEY, profile.money)
        editor.putString(PROFESSION, profile.profession)
        editor.putInt(LVL, profile.lvl)
        editor.putInt(SKILL, profile.skill)
        editor.putInt(STEP, profile.step)
        editor.apply()

        saveStats()

        Log.i("MYTAG", "onStop")
        startMyService()
        super.onStop()
    }

    override fun onDestroy() {
        unregisterReceiver(br)
        Log.i("MYTAG", "рессивер отключен")
        Log.i("MYTAG", "onDestroy")
        startMyService()
        super.onDestroy()

    }

    fun startMyService (){
        val intent=Intent(this,MyService::class.java)
        startService(intent)
    }


    override fun onResume() {
        Log.i("MYTAG", "onResume")
        startMyService()
        super.onResume()
        loadMoney()
    }

}