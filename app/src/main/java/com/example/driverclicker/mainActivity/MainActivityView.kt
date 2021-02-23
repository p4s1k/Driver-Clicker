package com.example.driverclicker.mainActivity

import com.example.driverclicker.basic.InterstitialAdInt
import com.example.driverclicker.basic.LoseRewardedAd
import com.example.driverclicker.basic.MoneyRewardedAdInt
import com.example.driverclicker.basic.ViewBasic
import com.google.android.gms.ads.rewarded.RewardedAdCallback

interface MainActivityView: ViewBasic, MoneyRewardedAdInt, InterstitialAdInt, LoseRewardedAd {
    fun showMoveAlert()
    fun showMoveRewardedAd(callback: RewardedAdCallback)
    fun hideMoveAdImage()


}