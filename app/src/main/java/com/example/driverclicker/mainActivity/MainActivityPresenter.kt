package com.example.driverclicker.mainActivity

import com.example.driverclicker.R
import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.repository.LocalRepository
import com.example.driverclicker.enums.ProfessionsEnum.*

class MainActivityPresenter(override val repository: LocalRepository, val view: MainActivityView) :
    PresenterBasic(repository, view) {

    fun carClick() {
        if (checkMovesValue()) {
            move()
            var a: Int
            val income: Int
            if (repository.getString(
                    SAVE,
                    PROFESSION,
                    Newspaper.tittle
                ) != "newspaper"
            ) {
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
            setStats(-10, 0, HEALTH)
            setStats(-10, 0, HUNGER)
            setStats(-10, 0, MOOD)
            checkLose()
        } else {
            move()
            showToast("Нет ходов")
        }
    }

    //check Profession out SP, set Income, set BackGround and set ImageCar
//    fun checkProfession() {
//        val enum=repository.getString(SAVE, PROFESSION, Newspaper.name)
//        view.changeBackGround(valueOf(enum).background)
//        view.changeImageCar(valueOf(enum).imageRes)
//        repository.saveInt(SAVE, INCOME, valueOf(enum).income)
//    }

    //load stats out of SP to Profile
    fun loadStats() {
        val array= mapOf(HEALTH to R.id.health_bar , HUNGER to R.id.hunger_bar, MOOD to R.id.mood_bar)
        for(i in array){
            showProgress(repository.getInt(SAVE, i.key, 100), i.value)
            checkLose(i.key)}
    }

    private fun checkLose(statsName: String) {
        val i= repository.getInt(SAVE,"alert$statsName", 6)
        if(i==6)return
        var move = "хода"
        if (i>4) move="ходов" else if (i<2) move = "ход"
        when(statsName){
            HEALTH->{showText("!$i $move!", R.id.health_alert_text )}
            HUNGER->{showText("!$i $move!", R.id.hunger_alert_text )}
            MOOD ->{showText("!$i $move!", R.id.mood_alert_text )}
        }
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
    }
}