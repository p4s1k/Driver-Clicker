package com.example.driverclicker.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

open class RepositoryApplicationClass() : Application() {
    companion object constants {
        const val SAVE = "save"
        const val STATS = "stats"
        const val ACCESS = "access"
        const val INCOME = "income"
        const val MONEY = "money"
        const val PROFESSION = "profession"
        const val LVL = "level"
        const val SKILL = "skill"
        const val STEP = "step"
        const val HEALTH = "health"
        const val HUNGER = "hunger"
        const val MOOD = "mood"
        const val SERVICESTEP = "servicestep"
    }

    lateinit var preferencesSave: SharedPreferences
    lateinit var preferencesStats: SharedPreferences
    lateinit var preferencesAccess: SharedPreferences

    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        Toast.makeText(this, context.toString(), Toast.LENGTH_LONG).show()
        LocalRepository.init(this)
    }

    open fun getAppContext(): Context? {
        return this
    }


//    private var sInstance: Context? = null
//
//    override fun onCreate() {
//        super.onCreate()
//        preferencesStats= getSharedPreferences("stats", Context.MODE_PRIVATE)
//        sInstance = this
//
//    }
//    open fun getInstance(): Context? {
//        return sInstance
//    }


//    abstract fun getDataString(fileName:String, key: String, defaultValue:String):String
//    abstract fun getDataInt(fileName: String, key: String, defaultValue: Int): Int
//    abstract fun getDataBoolean(fileName: String, key: String, defaultValue: Boolean): Boolean
}