package com.example.driverclicker.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

open class RepositoryApplicationClass : Application() {
    companion object constants {
        const val SAVE = "save"
        const val STATS = "stats"
        const val ACCESS = "access"
    }

    lateinit var preferencesSave: SharedPreferences
    lateinit var preferencesStats: SharedPreferences
    lateinit var preferencesAccess: SharedPreferences

    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        LocalRepository.init(this)
    }
}