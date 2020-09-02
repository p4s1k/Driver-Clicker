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
        showProgress(repository.getInt(SAVE, HEALTH, 100), R.id.health_bar)
        showProgress(repository.getInt(SAVE, HUNGER, 100), R.id.hunger_bar)
        showProgress(repository.getInt(SAVE, MOOD, 100), R.id.mood_bar)
    }


    private fun loadIncome(): Int {
        return repository.getInt(SAVE, INCOME, 1)
    }

//    fun saveIncome(int: Int) {
//        repository.saveInt(SAVE, INCOME, int)
//    }
}