package com.example.driverclicker.fragments.menuTwo

import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.enums.StatsEnum.*
import com.example.driverclicker.fragments.recyclerViews.data.StatusDataModel
import com.example.driverclicker.repository.RepositoryInt

class MenuTwoPresenter(view: MenuTwoView, repository: RepositoryInt) :
    PresenterBasic(repository, view) {

    private var hungerList = ArrayList<StatusDataModel>()
    fun initData(): ArrayList<StatusDataModel> {

        hungerList.add(
            StatusDataModel(
                Hunger0.name,
                Hunger0.title,
                Hunger0.desc,
                Hunger0.plusStat,
                Hunger0.minusStat,
                Hunger0.price,
                Hunger0.action
            )
        )

        hungerList.add(
            StatusDataModel(
                Hunger1.name,
                Hunger1.title,
                Hunger1.desc,
                Hunger1.plusStat,
                Hunger1.minusStat,
                Hunger1.price,
                Hunger1.action
            )
        )
        hungerList.add(
            StatusDataModel(
                Hunger2.name,
                Hunger2.title,
                Hunger2.desc,
                Hunger2.plusStat,
                Hunger2.minusStat,
                Hunger2.price,
                Hunger2.action
            )
        )
        hungerList.add(
            StatusDataModel(
                Hunger3.name,
                Hunger3.title,
                Hunger3.desc,
                Hunger3.plusStat,
                Hunger3.minusStat,
                Hunger3.price,
                Hunger3.action
            )
        )
        hungerList.add(
            StatusDataModel(
                Hunger4.name,
                Hunger4.title,
                Hunger4.desc,
                Hunger4.plusStat,
                Hunger4.minusStat,
                Hunger4.price,
                Hunger4.action
            )
        )
        hungerList.add(
            StatusDataModel(
                Hunger5.name,
                Hunger5.title,
                Hunger5.desc,
                Hunger5.plusStat,
                Hunger5.minusStat,
                Hunger5.price,
                Hunger5.action
            )
        )

        val achievedCount = repository.getInt(STATS, HUNGER, 0)
        for (i in 0..achievedCount) hungerList[i].achieved = true
        return hungerList
    }

    fun getAFavor(position: Int) {
        if (hungerList[position].achieved) {
            val plusStatValue = hungerList[position].plusStat
            val minusStatValue = hungerList[position].minusStat
            val priceValue = hungerList[position].price
            val money = loadMoney()
            if (money >= priceValue) {
                moneyMinus(priceValue)
                setStats(1, plusStatValue, HUNGER)
                setStats(minusStatValue, 0, HEALTH)
                setStats(minusStatValue, 0, MOOD)
                move()
                showToast(hungerList[position].action)
                showMoney()

            } else showToast("Недостаточно денег")
        } else showToast("Недоступно")
        checkLose()
    }

//    private fun moneyShow() {
//        val a = repository.getInt(SAVE, MONEY, 0)
//        showText("Деньги $a", R.id.text_money)
//    }

}