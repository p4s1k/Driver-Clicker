package com.example.driverclicker.fragments.menuTwo

import com.example.driverclicker.R
import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.enums.StatsEnum
import com.example.driverclicker.fragments.recyclerViews.data.StatusDataModel
import com.example.driverclicker.repository.RepositoryInt

class MenuTwoPresenter(view: MenuTwoView, repository: RepositoryInt) :
    PresenterBasic(repository, view) {

    private var hungerList = mutableListOf<StatusDataModel>()

    fun initData(): MutableList<StatusDataModel> {
        for(enum  in StatsEnum.values().copyOfRange(6,12)){
            hungerList.add(
                StatusDataModel(
                    enum.name,
                    enum.title,
                    enum.desc,
                    enum.plusHealth,
                    enum.minusHealth,
                    enum.price,
                    enum.action,
                    icon = enum.icon
                )
            )
        }

        val achievedCount = repository.getInt(STATS, HUNGER, 0)
        for (i in 0..achievedCount) hungerList[i].achieved = true
        return hungerList
    }

    fun getAFavor(position: Int) {
        val enum = enumValueOf<StatsEnum>(hungerList[position].name)
        if (hungerList[position].achieved) {
            val money = loadMoney()
            if (money >= enum.price) {
                moneyMinus(enum.price)
                setStats(enum.minusHealth, enum.plusHealth, HEALTH)
                setStats(enum.minusHunger, enum.plusHunger, HUNGER)
                setStats(enum.minusMood, enum.plusMood, MOOD)
                move(false)
                showToast(enum.action)
                showMoney()
            } else showToast(R.string.msg_not_enough_money)
        } else showToast(R.string.msg_not_available)
        checkLose()
    }

//    private fun moneyShow() {
//        val a = repository.getInt(SAVE, MONEY, 0)
//        showText("Деньги $a", R.id.text_money)
//    }

}