package com.example.driverclicker.mainActivity

import android.util.Log
import com.example.driverclicker.R
import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.enums.CarClick.*
import com.example.driverclicker.enums.ProfessionsEnum
import com.example.driverclicker.repository.LocalRepository
import com.example.driverclicker.enums.ProfessionsEnum.*
import com.example.driverclicker.enums.Progress.*
import com.example.driverclicker.repository.Ads
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import java.util.*


class MainActivityPresenter(override val repository: LocalRepository, val view: MainActivityView) :
    PresenterBasic(repository, view) {


    private var ads: Int

    init {
        ads = 20
    }


    private fun checkLoadedAndShowInterstitialAd() {
        if (ads > 0) {
            ads -= 1
        } else {
            ads = 20
            Ads.showInterstitialAd()
        }
    }

    private val MoneyRewardedAdLoadCallback = object : RewardedAdLoadCallback() {
        override fun onRewardedAdLoaded() {
            view.showToast("rewMoneyLoaded")
        }

        override fun onRewardedAdFailedToLoad(adError: LoadAdError) {
            view.showToast("rewMoneyFailed")
        }

    }

    private val MoneyRewardedAdCallback = object : RewardedAdCallback() {
        override fun onUserEarnedReward(p0: RewardItem) {
            moneyPlus(10 * loadIncome())
            showMoney()
            view.showToast("получено 500")
        }

        override fun onRewardedAdClosed() {
            createMoneyRewardedAd()
            super.onRewardedAdClosed()
        }
    }

    private fun checkLoadRewardedAd(rewardedAd: RewardedAd) {
        if (rewardedAd.isLoaded) {
            when (rewardedAd) {
                Ads.moneyRewardedAd -> showMoneyRewardedAd()
//                loseRewardedAd -> showLoseRewardedAd()
            }
        }
    }

    private fun showMoneyRewardedAd() {
        view.showMoneyRewardedAd(MoneyRewardedAdCallback)
    }

    fun data(): Int {
        if (repository.getInt(SAVE, SERVICESTEP, 250) == 250) return 0
        val date1 = repository.getLong(SAVE, "date", 0)
        val date2 = Date().time
        val progress = repository.getInt(SAVE, "serviceProgress", 0)
        var milliseconds = date2 - date1
        return if (milliseconds <= 0) {
            0
        } else {
            milliseconds /= 1000
            milliseconds += progress
            Log.i("mytag", milliseconds.toString())
            if (dataAddMove(milliseconds.toInt())) return 0
            val a = milliseconds.toInt() % 10
            Log.i("mytag", milliseconds.toString())
            a
        }
    }

    private fun dataAddMove(arg: Int): Boolean {
        var moves = repository.getInt(SAVE, SERVICESTEP, 249)
        return if (moves < 250) {
            moves += arg / 10
            if (moves > 250) {
                repository.saveInt(SAVE, SERVICESTEP, 250)
                true
            } else {
                repository.saveInt(SAVE, SERVICESTEP, moves)
                false
            }
        } else true
    }

    fun carClick() {
        checkLoadedAndShowInterstitialAd()
        if (checkMovesValue()) {
            move(true)
            var a: Int
            val income: Int
            val profName = repository.getString(SAVE, PROFESSION, Newspaper.name)
            if (profName != Newspaper.name) {
                a = loadIncome() / 10 * 8
                if (a == 0) {
                    a = loadIncome()
                }
                income = (a..loadIncome()).random()
            } else {
                income = loadIncome()
            }
            moneyPlus(income)
            showMoney()
            setStats(ProfessionsEnum.valueOf(profName).clickMin, ProfessionsEnum.valueOf(profName).clickMax, HEALTH)
            setStats(ProfessionsEnum.valueOf(profName).clickMin, ProfessionsEnum.valueOf(profName).clickMax, HUNGER)
            setStats(ProfessionsEnum.valueOf(profName).clickMin, ProfessionsEnum.valueOf(profName).clickMax, MOOD)
            checkLose()
        } else {
            move(true)
            showToast(R.string.no_moves)
        }
    }

    //load stats out of SP to Profile
    private fun loadStats() {
        val array =
            mapOf(HEALTH to R.id.health_bar, HUNGER to R.id.hunger_bar, MOOD to R.id.mood_bar)

        for (i in array) {
            showProgress(repository.getInt(SAVE, i.key, 100), i.value)
            checkLose(i.key)
        }
    }

    private fun checkLose(statsName: String) {
        val i = repository.getInt(SAVE, "alert$statsName", 6)
        if (i == 6) return
        var move = "хода"
        if (i > 4) move = "ходов" else if (i < 2) move = "ход"
        when (statsName) {
            HEALTH -> {
                showText("!$i $move!", R.id.health_alert_text)
            }
            HUNGER -> {
                showText("!$i $move!", R.id.hunger_alert_text)
            }
            MOOD -> {
                showText("!$i $move!", R.id.mood_alert_text)
            }
        }
    }

    private val movesAdLoadCallback = object : RewardedAdLoadCallback() {
        override fun onRewardedAdLoaded() {
            super.onRewardedAdLoaded()
        }

        override fun onRewardedAdFailedToLoad(p0: Int) {
        }
    }

    private val movesAdCallback = object : RewardedAdCallback() {
        override fun onUserEarnedReward(p0: RewardItem) {
            addMove(ServiceAdReward.value)
            view.hideMoveAdImage()
        }

        override fun onRewardedAdClosed() {
            createMoveRewardedAd(movesAdLoadCallback)
        }

        override fun onRewardedAdFailedToShow(p0: Int) {
            createMoveRewardedAd(movesAdLoadCallback)
            view.showToast("error")
            if (!viewBasic.isNetworkAvailable()) {
                viewBasic.showToast("проверьте подключение к интернету")
            } else {
                viewBasic.showToast("видео не загружено, попробуйте через несколько секунд")
            }
        }
    }

    fun showMoveRewardedAd() {
        view.showMoveRewardedAd(movesAdCallback)
    }

    private fun addMove(value: Int) {
        val moves = repository.getInt(SAVE, SERVICESTEP, 249)
        val actualValue = moves + value
        if (actualValue >= 249) {
            repository.saveInt(SAVE, SERVICESTEP, 249)
        } else repository.saveInt(SAVE, SERVICESTEP, actualValue)
        showMoveValue()
    }

    private fun loadIncome(): Int {
        return repository.getInt(SAVE, INCOME, 1)
    }

    fun start() {
        changeProfession() //check Profession проверяет профу и
        progressCheck()   //check Progress (level)
        showMoney()
        loadStats()     //load Stats out of SPe
        showMoveValue()     //set steps to View
        createMoneyRewardedAd()
        createMoveRewardedAd(movesAdLoadCallback)
        createLoseRewardedAd()
        checkLose()
    }

    private fun createMoneyRewardedAd() {
        Ads.moneyRewardedAd =
            Ads.createAndLoadRewardedAd(Ads.moneyAdUnit, MoneyRewardedAdLoadCallback)
    }

    fun moveImageClick() {
        view.showMoveAlert()
    }
}
