package com.example.driverclicker.basic

import com.google.android.gms.ads.rewarded.RewardedAdCallback

interface MoneyRewardedAdInt: BasicRewardedAd{
    fun showMoneyRewardedAd(callback: RewardedAdCallback)
}