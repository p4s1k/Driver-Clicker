@file:Suppress("PropertyName", "MemberVisibilityCanBePrivate")

package com.example.driverclicker.basic

import android.annotation.SuppressLint
import android.util.Log
import com.example.driverclicker.R
import com.example.driverclicker.enums.ProfessionsEnum
import com.example.driverclicker.enums.Progress
import com.example.driverclicker.enums.Progress.*
import com.example.driverclicker.repository.Ads
import com.example.driverclicker.repository.RepositoryInt
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback


//(open val repository:RepositoryInt, open val view: MenuOneTwoThreeView)
open class PresenterBasic(open val repository: RepositoryInt, open val viewBasic: ViewBasic) {
    val SAVE = "save"//FileName SP
    val STATS = "stats"

    // SP KEYS
    val MONEY = "money"
    val PROFESSION = "profession"
    val LVL = "level"
    val SKILL = "skill"
    val STEP = "step"
    val HEALTH = "health"
    val HUNGER = "hunger"
    val INCOME = "income"
    val MOOD = "mood"
    val SERVICESTEP = "servicestep"
    val ACCESS = "access"


    fun checkMovesValue(): Boolean {
        return loadMoveValue() > 0
    }

    //step logic. -Profile, Save in SP, setStep(), ProgressUP() and start Service
    fun move(boolean: Boolean) {
        if (checkMovesValue()) {
            var moveValue = loadMoveValue()
            Log.i("MYTAG", "move() получил $moveValue")
            moveValue -= 1
            saveMoveValue(moveValue)
            Log.i("MYTAG", "move() отнял шаг и сохранил $moveValue")
            showMoveValue()
            if (boolean) progressUp()
        }
        viewBasic.startService()
    }

    //progress up logic.
    @SuppressLint("SetTextI18n")
    fun progressUp() {
        val viewPb = (R.id.experience_bar)
        var skill = loadSkill()
        var step = loadStep()
        skill += step
        showProgress(skill, viewPb)
        if (viewBasic.checkProgressMax(viewPb)) {
            var level = loadLevel()
            level += 1
            if (step > Stop.value) {
                step -= Subtract.value
                saveStep(step)
            }
            skill = 0
            showText("Ур. $level", R.id.text_lvl)
            showProgress(skill, viewPb)
            saveLevel(level)
        }
        saveSkill(skill)
        if (loadLevel() == 80) win()
    }

    private fun win() {
        //победа
    }

    //progress check logic. load out of SP, set to Profile and set to View
    fun progressCheck() {
        val viewPb = (R.id.experience_bar)
        val viewLvl = (R.id.text_lvl)
        val lvl = loadLevel()
        val skill = loadSkill()
        viewBasic.showProgress(skill, viewPb)
        viewBasic.showText("Ур. $lvl", viewLvl)
    }


    private fun saveLevel(int: Int) {
        repository.saveInt(SAVE, LVL, int)
    }

    private fun loadLevel(): Int {
        return repository.getInt(SAVE, LVL, 0)
    }

    private fun loadSkill(): Int {
        return repository.getInt(SAVE, SKILL, 0)
    }

    private fun saveSkill(int: Int) {
        repository.saveInt(SAVE, SKILL, int)
    }

    private fun loadStep(): Int {
        return repository.getInt(SAVE, STEP, Step.value)
    }

    private fun saveStep(int: Int) {
        repository.saveInt(SAVE, STEP, int)
    }

    private fun saveMoveValue(int: Int) {
        repository.saveInt(SAVE, SERVICESTEP, int)
    }

    //load steps out SP to Profile
    fun loadMoveValue(): Int {
        return repository.getInt(SAVE, SERVICESTEP, 250)
    }

    //set steps in View of Profile
    fun showMoveValue() {
        val moveValue = repository.getInt(SAVE, SERVICESTEP, 250)
        viewBasic.showText(moveValue.toString(), R.id.text_movesValue)
        Log.i("MYTAG", "showMoveValue() Отрисовывает $moveValue")
        if (moveValue <= Progress.ServiceValueForShowImage.value) showMoveAdImageVisibility()
    }


    private fun showMoveAdImageVisibility() {
        if (!viewBasic.isMoveAdVisibility()) {
            viewBasic.changeMoveAdImageVisibility(true)
        }
    }

    fun showText(str: String, viewId: Int) {
        viewBasic.showText(str, viewId)
    }


    fun showToast(stringResourceValue: Int) {
        viewBasic.showToast(stringResourceValue)
    }

    fun showToast(list: List<Int>) {
        var str = ""
        list.forEach {
            str += it
        }
        viewBasic.showToast(list)
    }

    fun showToast(map: Map<String, Int>) {

//        var str=""
//
//        for(a in 0..map.size){
//            if (map.containsKey("$a")){
//                if (a==3 || a==5){
//                    str+=map[a].toString()
//                }
//                str+=map[a]
//            }
//        }
        viewBasic.showToast(map)
    }

    fun loadMoney(): Int {
        return repository.getInt(SAVE, MONEY, 0)
//        text_money.text="Деньги "+profile.money.toString()
    }

    private fun saveMoney(int: Int) {
        repository.saveInt(SAVE, MONEY, int)
    }

    //Money plus logic.
    @SuppressLint("SetTextI18n")
    fun moneyPlus(income: Int) {
        var money = repository.getInt(SAVE, MONEY, 0)
        money += income
        saveMoney(money)
//        profile.money+=income

    }

    fun showProgress(int: Int, id: Int) {
        viewBasic.showProgress(int, id)
    }


    //Money minus logic.
    @SuppressLint("SetTextI18n")
    fun moneyMinus(count: Int) {
        var money = repository.getInt(SAVE, MONEY, 0)
        money -= count
        repository.saveInt(SAVE, MONEY, money)
    }

    //change stat in Profile and set in View
    fun setStats(minValue: Int, maxValue: Int, statsName: String) {
        val countResult = (minValue..maxValue).random()
        var statValue = repository.getInt(SAVE, statsName, 100)
        val a = statValue
        statValue += countResult
        if (a == 0 && statValue > 0) resetLose(statsName)
        if (statValue <= 0) {
            statValue = 0
            alertLose(statsName)
        }
        if (statValue > 100) statValue = 100
        when (statsName) {
            HEALTH -> viewBasic.showProgress(statValue, R.id.health_bar)
            HUNGER -> viewBasic.showProgress(statValue, R.id.hunger_bar)
            MOOD -> viewBasic.showProgress(statValue, R.id.mood_bar)
        }
        repository.saveInt(SAVE, statsName, statValue)
    }

    private fun alertLose(statsName: String) {
        var i = repository.getInt(SAVE, "alert$statsName", 6)
        i--
        var move = "хода"
        if (i > 4 || i == 0) move = "ходов" else if (i < 2) move = "ход"
        when (statsName) {
            HEALTH -> {
                showText(" !$i $move!", R.id.health_alert_text)
            }
            HUNGER -> {
                showText("!$i $move!", R.id.hunger_alert_text)
            }
            MOOD -> {
                showText("!$i $move!", R.id.mood_alert_text)
            }
        }
        repository.saveInt(SAVE, "alert$statsName", i)
    }


    fun showMoney() {
        showText("Деньги ${loadMoney()}", R.id.text_money)
    }

    private fun resetLose(statsName: String) {
        repository.saveInt(SAVE, "alert$statsName", 6)
        when (statsName) {
            HEALTH -> (viewBasic.resetLose(R.id.health_alert_text))
            HUNGER -> (viewBasic.resetLose(R.id.hunger_alert_text))
            MOOD -> (viewBasic.resetLose(R.id.mood_alert_text))
        }
    }

    fun checkLose() {
        val list = arrayOf(HEALTH, HUNGER, MOOD)
        for (i in list) {
            if (repository.getInt(SAVE, "alert$i", 6) < 1) {
                viewBasic.closeFragment()
                viewBasic.showAlert()
                return
            }
        }
    }

    fun restart() {
        repository.clearRepository()
        showMoveValue()
        viewBasic.stopService()
        changeProfession()
        loadMoveValue()
        progressCheck()
        loadStats()
        showMoney()
        resetLose(HEALTH)
        resetLose(HUNGER)
        resetLose(MOOD)
        viewBasic.closeFragment()
        showToast(R.string.message_lose)
    }

    private fun loadStats() {
        val array =
            mapOf(HEALTH to R.id.health_bar, HUNGER to R.id.hunger_bar, MOOD to R.id.mood_bar)
        for (i in array) {
            showProgress(repository.getInt(SAVE, i.key, 100), i.value)
            checkLose()
        }
    }


    fun changeProfession() {
        val enum = repository.getString(SAVE, PROFESSION, ProfessionsEnum.Newspaper.name)
        viewBasic.changeBackgroundMain(ProfessionsEnum.valueOf(enum).background)
        viewBasic.changeImageCarMain(ProfessionsEnum.valueOf(enum).imageRes)
        repository.saveInt(SAVE, INCOME, ProfessionsEnum.valueOf(enum).income)
    }

    private val LoseRewardedAdLoadCallBack = object : RewardedAdLoadCallback() {
        override fun onRewardedAdLoaded() {
            viewBasic.showToast("rewLoseLoaded")
        }

        override fun onRewardedAdFailedToLoad(p0: Int) {
            viewBasic.showToast("rewLoseFailed")
        }
    }

    private val LoseRewardedAdCallback = object : RewardedAdCallback() {
        override fun onUserEarnedReward(p0: RewardItem) {
            repository.saveInt(SAVE, HEALTH, 50)
            repository.saveInt(SAVE, HUNGER, 50)
            repository.saveInt(SAVE, MOOD, 50)
            viewBasic.showProgress(50, R.id.health_bar)
            viewBasic.showProgress(50, R.id.hunger_bar)
            viewBasic.showProgress(50, R.id.mood_bar)
            resetLose(HEALTH)
            resetLose(HUNGER)
            resetLose(MOOD)
            checkLose()
        }

        override fun onRewardedAdClosed() {
            checkLose()
            createLoseRewardedAd()
            super.onRewardedAdClosed()
        }

    }

    fun createLoseRewardedAd() {
        Ads.loseRewardedAd = Ads.createAndLoadRewardedAd(Ads.loseAdUnit, LoseRewardedAdLoadCallBack)
    }

    fun createMoveRewardedAd(callback: RewardedAdLoadCallback) {
        Ads.movesRewardedAd = Ads.createAndLoadRewardedAd(Ads.movesAdUnit, callback)
    }

    fun tryPositive(): Boolean {
        return if (Ads.loseRewardedAd.isLoaded) {
            showLoseRewardedAd()
            true
        } else {
            if (!viewBasic.isNetworkAvailable()) {
                viewBasic.showToast("проверьте подключение к интернету")
            } else {
                viewBasic.showToast("видео не загружено, попробуйте через несколько секунд")
            }
            createLoseRewardedAd()
            false
        }
    }


    fun showLoseRewardedAd() {
        viewBasic.showLoseRewardedAd(LoseRewardedAdCallback)
    }


    fun startAlert() {
        viewBasic.startAlert()
    }
}