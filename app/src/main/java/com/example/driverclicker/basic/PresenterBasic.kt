@file:Suppress("PropertyName", "MemberVisibilityCanBePrivate")

package com.example.driverclicker.basic

import android.annotation.SuppressLint
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
        if (loadLevel()==80)win()
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
        val a =statValue
        statValue+=countResult
        if (a==0 && statValue>0) resetLose(statsName)
        if(statValue<=0) {
            statValue=0
            viewBasic.closeFragment()
            alertLose(statsName)
        }
        if (statValue>100) statValue=100
        when(statsName){
            HEALTH->viewBasic.showProgress(statValue,R.id.health_bar)
            HUNGER->viewBasic.showProgress(statValue,R.id.hunger_bar)
            MOOD->viewBasic.showProgress(statValue,R.id.mood_bar)
        }
        repository.saveInt(SAVE, statsName, statValue)

    }

    private fun alertLose(statsName: String){
        var i= repository.getInt(SAVE,"alert$statsName", 6)
        i--
        var move = "хода"
        if (i>4) move="ходов" else if (i<2) move = "ход"
        when(statsName){
            HEALTH->{showText(" !$i $move!", R.id.health_alert_text )}
            HUNGER->{showText("!$i $move!", R.id.hunger_alert_text )}
            MOOD ->{showText("!$i $move!", R.id.mood_alert_text )}
        }
        repository.saveInt(SAVE, "alert$statsName", i)
    }


    fun showMoney() {
        showText("Деньги ${loadMoney()}", R.id.text_money)
    }

    private fun resetLose(statsName: String){
        repository.saveInt(SAVE, "alert$statsName", 6)
        when(statsName){
            HEALTH->(viewBasic.resetLose(R.id.health_alert_text))
            HUNGER->(viewBasic.resetLose(R.id.hunger_alert_text))
            MOOD->(viewBasic.resetLose(R.id.mood_alert_text))
        }
    }

    fun checkLose() {
        val a= arrayOf(HEALTH, HUNGER, MOOD)
        for (i in a){
            if (repository.getInt(SAVE, "alert$i",6)<1){
                restart()
                return
            }
        }
    }

    fun restart (){
        repository.clearRepository()
        showMoveValue()
        viewBasic.closeFragment()
        changeProfession()
        loadMoveValue()
        progressCheck()
        loadStats()
        showMoney()
        resetLose(HEALTH)
        resetLose(HUNGER)
        resetLose(MOOD)
        showToast("ВЫ ПРОИГРАЛИ!")
    }

    private fun loadStats() {
        val array= mapOf(HEALTH to R.id.health_bar , HUNGER to R.id.hunger_bar, MOOD to R.id.mood_bar)
        for(i in array){
            showProgress(repository.getInt(SAVE, i.key, 100), i.value)
            checkLose()}
    }



    fun changeProfession(){
        val enum=repository.getString(SAVE, PROFESSION, ProfessionsEnum.Newspaper.name)
        viewBasic.changeBackgroundMain(ProfessionsEnum.valueOf(enum).background)
        viewBasic.changeImageCarMain(ProfessionsEnum.valueOf(enum).imageRes)
        repository.saveInt(SAVE, INCOME, ProfessionsEnum.valueOf(enum).income)
    }
}