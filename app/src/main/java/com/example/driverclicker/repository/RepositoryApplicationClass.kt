package com.example.driverclicker.repository

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.driverclicker.mainActivity.MainActivity
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardedAd

open class RepositoryApplicationClass : Application() {
    companion object constants {
        const val SAVE = "save"
        const val STATS = "stats"
        const val ACCESS = "access"
    }

    lateinit var preferencesSave: SharedPreferences
    lateinit var preferencesStats: SharedPreferences
    lateinit var preferencesAccess: SharedPreferences

    lateinit var mInterstitialAd: InterstitialAd
    lateinit var moneyRewardedAd: RewardedAd
    lateinit var loseRewardedAd: RewardedAd

    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        Ads.init(this)
        LocalRepository.init(this)
    }
}