package com.example.driverclicker.basic

import com.google.android.gms.ads.rewarded.RewardedAdCallback

interface LoseRewardedAd:BasicRewardedAd {
    fun showLoseRewardedAd(callback: RewardedAdCallback)
}