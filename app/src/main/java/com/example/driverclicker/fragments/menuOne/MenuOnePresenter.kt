package com.example.driverclicker.fragments.menuOne

import com.example.driverclicker.R
import com.example.driverclicker.basic.ViewBasic
import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.enums.StatsEnum
import com.example.driverclicker.fragments.recyclerViews.data.StatusDataModel
import com.example.driverclicker.repository.RepositoryInt


class MenuOnePresenter(override val viewBasic: ViewBasic, override val repository: RepositoryInt) :
    PresenterBasic(repository, viewBasic) {
    private var healthList = mutableListOf<StatusDataModel>()
    fun initHealth(): MutableList<StatusDataModel> {
        for (enum in StatsEnum.values().copyOfRange(0, 6)) {
            healthList.add(
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
        val achievedCount = repository.getInt(STATS, HEALTH, 0)
        for (i in 0..achievedCount) healthList[i].achieved = true
        return healthList
    }

    fun getAFavor(position: Int) {
        val enum = enumValueOf<StatsEnum>(healthList[position].name)
        if (healthList[position].achieved) {
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
}