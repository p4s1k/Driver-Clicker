package com.example.driverclicker.fragments.menuOne

import com.example.driverclicker.basic.ViewBasic
import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.enums.StatsEnum.*
import com.example.driverclicker.fragments.recyclerViews.data.StatusDataModel
import com.example.driverclicker.repository.RepositoryInt


class MenuOnePresenter(override val viewBasic: ViewBasic, override val repository: RepositoryInt): PresenterBasic(repository, viewBasic) {
    private var healthList = ArrayList<StatusDataModel>()
    fun initHealth(): ArrayList<StatusDataModel>{

        healthList.add(
            StatusDataModel(
                Health0.name,
                Health0.title,
                Health0.desc,
                Health0.plusStat,
                Health0.minusStat,
                Health0.price,
                Health0.action
            )
        )

        healthList.add(
            StatusDataModel(
                Health1.name,
                Health1.title,
                Health1.desc,
                Health1.plusStat,
                Health1.minusStat,
                Health1.price,
                Health1.action
            )
        )
        healthList.add(
            StatusDataModel(
                Health2.name,
                Health2.title,
                Health2.desc,
                Health2.plusStat,
                Health2.minusStat,
                Health2.price,
                Health2.action
            )
        )
        healthList.add(
            StatusDataModel(
                Health3.name,
                Health3.title,
                Health3.desc,
                Health3.plusStat,
                Health3.minusStat,
                Health3.price,
                Health3.action
            )
        )
        healthList.add(
            StatusDataModel(
                Health4.name,
                Health4.title,
                Health4.desc,
                Health4.plusStat,
                Health4.minusStat,
                Health4.price,
                Health4.action
            )
        )
        healthList.add(
            StatusDataModel(
                Health5.name,
                Health5.title,
                Health5.desc,
                Health5.plusStat,
                Health5.minusStat,
                Health5.price,
                Health5.action)
        )
        val achievedCount= repository.getInt(STATS, HEALTH, 0)
        for(i in 0..achievedCount ) healthList[i].achieved=true
        return healthList
    }

    fun getAFavor(position: Int){
        if(healthList[position].achieved){
            val plusStatValue = healthList[position].plusStat
            val minusStatValue = healthList[position].minusStat
            val priceValue = healthList[position].price
            val money = loadMoney()
            if(money>=priceValue) {
                moneyMinus(priceValue)
                setStats(1,plusStatValue,HEALTH)
                setStats(minusStatValue,0,HUNGER)
                setStats(minusStatValue,0,MOOD)
                move()
                showToast(healthList[position].action)
                showMoney()
            } else showToast("Недостаточно денег")
        }else showToast("Недоступно")
        checkLose()
    }
}