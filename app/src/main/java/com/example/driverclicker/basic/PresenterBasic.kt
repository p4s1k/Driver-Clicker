package com.example.driverclicker.basic

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.util.Log
import com.example.driverclicker.R
import com.example.driverclicker.enums.ProfessionsEnum
import com.example.driverclicker.repository.RepositoryInt

//(open val repository:RepositoryInt, open val view: MenuOneTwoThreeView)
abstract class PresenterBasic(open val repository: RepositoryInt, open val viewBasic: ViewBasic) {
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
    fun move() {
        if (checkMovesValue()) {
            var moveValue = loadMoveValue()
            Log.i("MYTAG", "move() получил $moveValue")
            moveValue -= 1
            saveMoveValue(moveValue)
            Log.i("MYTAG", "move() отнял шаг и сохранил $moveValue")
            showMoveValue()
            progressUp()
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
            if (step > 250) {
                step -= 10
                saveStep(step)
            }
            skill = 0
            showText("Ур. $level", R.id.text_lvl)
            showProgress(skill, viewPb)
            saveLevel(level)
        }
        saveSkill(skill)
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
        return repository.getInt(SAVE, STEP, 800)
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
        val moveValue = repository.getInt(SAVE, SERVICESTEP, 250).toString()
        viewBasic.showText(moveValue, R.id.text_movesValue)
        Log.i("MYTAG", "showMoveValue() Отрисовывает $moveValue")
    }

    fun showText(str: String, viewId: Int) {
        viewBasic.showText(str, viewId)
    }

    fun showToast(str: String) {
        viewBasic.showToast(str)
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

//        profile.money-=count
//        text_money.text="Деньги "+profile.money.toString()
    }

    //change stat in Profile and set in View
    fun setStats(minValue: Int, maxValue: Int, statsName: String) {
        val countResult = (minValue..maxValue).random()
        var statValue = repository.getInt(SAVE, statsName, 100)
        statValue+=countResult
        if(statValue<0) {
            statValue=0
            viewBasic.closeFragment()
        }
        if (statValue>100) statValue=100
        when(statsName){
            HEALTH->viewBasic.showProgress(statValue,R.id.health_bar)
            HUNGER->viewBasic.showProgress(statValue,R.id.hunger_bar)
            MOOD->viewBasic.showProgress(statValue,R.id.mood_bar)
        }
        repository.saveInt(SAVE, statsName, statValue)
    }

    fun showMoney() {
        showText("Деньги ${loadMoney()}", R.id.text_money)
    }

    private fun restart (){
        repository.clearRepository()
        showMoveValue()
        viewBasic.closeFragment()
//        checkProfession()
        loadMoveValue()
        progressCheck()
        loadStats()
        showMoney()
    }

    private fun loadStats() {
        showProgress(repository.getInt(SAVE, HEALTH, 100), R.id.health_bar)
        showProgress(repository.getInt(SAVE, HUNGER, 100), R.id.hunger_bar)
        showProgress(repository.getInt(SAVE, MOOD, 100), R.id.mood_bar)
    }

    fun changeProfession(){
        val enum=repository.getString(SAVE, PROFESSION, ProfessionsEnum.Newspaper.name)
        viewBasic.changeBackgroundMain(ProfessionsEnum.valueOf(enum).background)
        viewBasic.changeImageCarMain(ProfessionsEnum.valueOf(enum).imageRes)
        repository.saveInt(SAVE, INCOME, ProfessionsEnum.valueOf(enum).income)
    }
}