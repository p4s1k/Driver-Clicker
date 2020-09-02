package com.example.driverclicker.fragments.menuThree

import com.example.driverclicker.basic.ViewBasic
import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.enums.StatsEnum.*
import com.example.driverclicker.fragments.recyclerViews.data.StatusDataModel
import com.example.driverclicker.repository.RepositoryInt

class MenuThreePresenter(view: ViewBasic, repository: RepositoryInt) :
    PresenterBasic(repository, view) {

    private var moodList = ArrayList<StatusDataModel>()

    fun initData(): ArrayList<StatusDataModel> {

        moodList.add(
            StatusDataModel(
                Mood0.name,
                Mood0.title,
                Mood0.desc,
                Mood0.plusStat,
                Mood0.minusStat,
                Mood0.price,
                Mood0.action
            )
        )
        moodList.add(
            StatusDataModel(
                Mood1.name,
                Mood1.title,
                Mood1.desc,
                Mood1.plusStat,
                Mood1.minusStat,
                Mood1.price,
                Mood1.action
            )
        )
        moodList.add(
            StatusDataModel(
                Mood2.name,
                Mood2.title,
                Mood2.desc,
                Mood2.plusStat,
                Mood2.minusStat,
                Mood2.price,
                Mood2.action
            )
        )
        moodList.add(
            StatusDataModel(
                Mood3.name,
                Mood3.title,
                Mood3.desc,
                Mood3.plusStat,
                Mood3.minusStat,
                Mood3.price,
                Mood3.action
            )
        )
        moodList.add(
            StatusDataModel(
                Mood4.name,
                Mood4.title,
                Mood4.desc,
                Mood4.plusStat,
                Mood4.minusStat,
                Mood4.price,
                Mood4.action
            )
        )
        moodList.add(
            StatusDataModel(
                Mood5.name,
                Mood5.title,
                Mood5.desc,
                Mood5.plusStat,
                Mood5.minusStat,
                Mood5.price,
                Mood5.action
            )
        )

        val achievedCount = repository.getInt(STATS, MOOD, 0)
        for (i in 0..achievedCount) moodList[i].achieved = true
        return moodList
    }

    fun getAFavor(position: Int) {
        if (moodList[position].achieved) {
            val plusStatValue = moodList[position].plusStat
            val minusStatValue = moodList[position].minusStat
            val priceValue = moodList[position].price
            val money = loadMoney()
            if (money >= priceValue) {
                moneyMinus(priceValue)
                setStats(1, plusStatValue, MOOD)
                setStats(minusStatValue, 0, HEALTH)
                setStats(minusStatValue, 0, HUNGER)
                move()
                showToast(moodList[position].action)
                showMoney()

            } else showToast("Недостаточно денег")
        } else showToast("Недоступно")
    }
}