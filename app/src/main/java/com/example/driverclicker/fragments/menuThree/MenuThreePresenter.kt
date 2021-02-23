package com.example.driverclicker.fragments.menuThree

import com.example.driverclicker.R
import com.example.driverclicker.basic.ViewBasic
import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.enums.StatsEnum
import com.example.driverclicker.fragments.recyclerViews.data.StatusDataModel
import com.example.driverclicker.repository.RepositoryInt

class MenuThreePresenter(view: ViewBasic, repository: RepositoryInt) :
    PresenterBasic(repository, view) {

    private var moodList = mutableListOf<StatusDataModel>()

    fun initData(): MutableList<StatusDataModel> {
        for (enum in StatsEnum.values().copyOfRange(12, 18)) {
            moodList.add(
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

        val achievedCount = repository.getInt(STATS, MOOD, 0)
        for (i in 0..achievedCount) moodList[i].achieved = true
        return moodList
    }

    fun getAFavor(position: Int) {
        val enum = enumValueOf<StatsEnum>(moodList[position].name)
        if (moodList[position].achieved) {
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