package com.example.driverclicker.fragments.menuFour

import com.example.driverclicker.R
import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.enums.StatsEnum
import com.example.driverclicker.fragments.recyclerViews.data.WorkDataModel
import com.example.driverclicker.repository.RepositoryInt
import com.example.driverclicker.enums.UpgradeEnum

class MenuFourPresenter(val view: MenuFourView, override val repository: RepositoryInt) :
    PresenterBasic(repository, view) {
    private var worksList = mutableListOf<WorkDataModel>()

    fun initData(): MutableList<WorkDataModel> {
        for (i in UpgradeEnum.values()){
            worksList.add(
                WorkDataModel(
                    i.type,
                    i.name,
                    i.tittle,
                    i.desc,
                    i.price,
                    i.level,
                    false,
                    i.icon,
                    i.openingUpgrade
                )
            )
        }
        for (i in 0 until worksList.size) {
            worksList[i].achieved = repository.getBoolean(ACCESS, worksList[i].name, false)
        }
        return worksList
    }

    //change Profession logic. take new Profession and save in SP. checkProfession()
    private fun changeProfession(professionName: String) {
        repository.saveString(SAVE, PROFESSION, professionName)
    }

    //check choose logic.
    fun checkChoose(position: Int) {
        // если получено, то выходим и отправляем сообщение
        if (worksList[position].achieved) return showToast(R.string.msg_polucheno)
        //если нет:

        var accessText = mutableMapOf<String, Int>()
        accessText["1"] = R.string.accessText_need
        var accessLevel = false
        var accessMoney = false
        val price = worksList[position].price
        val level = worksList[position].level
        if (position != 0) if (!worksList[position - 1].achieved) accessText ["2"] = worksList[position - 1].tittle   //открыт ли апгрейд
        if (repository.getInt(SAVE, LVL, 0) >= level) {
            accessLevel = true
        } else {
            accessText["3"] = level
            accessText["4"] = R.string.accessText_lvl
        } //подходит ли уровень
        if (repository.getInt(SAVE, MONEY, 0) >= price) {
            accessMoney = true
        } else{
            accessText["5"]= price
            accessText["6"]= R.string.accessText_rubels
        }  //хватает ли денег

        //исход событий в зависимостий при доступном или недоступном разрешении
        if (position != 0) {
            if (worksList[position - 1].achieved && accessLevel && accessMoney) {  // если не первый элемент и разрешения по всем пунктам, то good()
                good(position)
            } else bad(accessText)                                                         // есди не первый элемент и не все разрешения, то bad()
        } else if (accessLevel && accessMoney) {
            good(position)
        } else bad(accessText)
    }

    // Good logic.
    private fun good(position: Int) {                   //если все проверки пройдены

        val list = mutableListOf<Int>(R.string.priobreteno, worksList[position].tittle)

        moneyMinus(worksList[position].price)                // отнимаем деньги
        showMoney()
        repository.saveBoolean(ACCESS, worksList[position].name, true)
//        var upgradeText = 0
        if (worksList[position].type == PROFESSION) {
            changeProfession(worksList[position].openestUpgrade)     //если профессия, то поменять
        } else {
            //если апгрейд
            repository.saveInt(STATS, worksList[position].type, repository.getInt(STATS, worksList[position].type,0)+1)
            if (worksList[position].openestUpgrade!=""){
                list.add(R.string.upgradeOpen)
                list.add(StatsEnum.valueOf(worksList[position].openestUpgrade).title)
            }
        }
//        showToast("Приобретено ${worksList[position].tittle} $upgradeText")       //в конце выводит сообщение о приобритении
        showToast(list)       //в конце выводит сообщение о приобритении
        view.closeFragment() //закрыть фрагмент
        changeProfession()
    }

    //bad logic.
    private fun bad(accessText: MutableMap<String, Int>) {
        accessText.toMap()
        showToast(accessText)
    } //если проверки не пройдены, то выводит об этом сообщение
}