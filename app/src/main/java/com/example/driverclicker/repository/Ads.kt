package com.example.driverclicker.repository

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

object Ads: RepositoryApplicationClass() {

    const val loseAdUnit = "ca-app-pub-3940256099942544/5224354917"
    const val moneyAdUnit = "ca-app-pub-3940256099942544/5224354917"
    const val movesAdUnit = "ca-app-pub-3940256099942544/5224354917"
    private const val moveAdUnit = "ca-app-pub-3940256099942544/1033173712"
    lateinit var contextA: Context
    lateinit var movesRewardedAd: RewardedAd

   fun init (context: Context) {
        MobileAds.initialize(context) {}

        mInterstitialAd = InterstitialAd(context)

        mInterstitialAd.adUnitId= moveAdUnit

        mInterstitialAd.loadAd(AdRequest.Builder().build())

       mInterstitialAd.adListener = object : AdListener(){

           override fun onAdClosed() {
               super.onAdClosed()
               mInterstitialAd.loadAd(AdRequest.Builder().build())
           }
       }
       contextA=context

    }

    fun createAndLoadRewardedAd(adUnitId: String, callback: RewardedAdLoadCallback): RewardedAd {
        val newRewardedAd = RewardedAd(contextA, adUnitId)
        newRewardedAd.loadAd(AdRequest.Builder().build(), callback)
        return newRewardedAd
    }



    fun showInterstitialAd (){
        if (mInterstitialAd.isLoaded){
            mInterstitialAd.show()
            mInterstitialAd.loadAd(AdRequest.Builder().build())
        } else{
            Log.d("ADTAG", "The interstitial wasn't loaded yet.")
        }
    }

}