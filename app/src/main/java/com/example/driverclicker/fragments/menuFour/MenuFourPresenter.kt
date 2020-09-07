package com.example.driverclicker.fragments.menuFour

import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.fragments.recyclerViews.data.WorkDataModel
import com.example.driverclicker.repository.RepositoryInt
import com.example.driverclicker.enums.UpgradeEnum

class MenuFourPresenter(val view: MenuFourView, override val repository: RepositoryInt) :
    PresenterBasic(repository, view) {
    private var worksList = ArrayList<WorkDataModel>()
    fun initData(): ArrayList<WorkDataModel> {
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
        if (worksList[position].achieved) return showToast("Уже получено")
        //если нет:
        var accessText = "Требуется: \n"
        var accessLevel = false
        var accessMoney = false
        val price = worksList[position].price
        val level = worksList[position].level
        if (position != 0) if (!worksList[position - 1].achieved) accessText += worksList[position - 1].tittle   //открыт ли апгрейд
        if (repository.getInt(SAVE, LVL, 0) >= level) {
            accessLevel = true
        } else accessText += "\n$level уровень" //подходит ли уровень
        if (repository.getInt(SAVE, MONEY, 0) >= price) {
            accessMoney = true
        } else accessText += "\n$price рублей"  //хватает ли денег

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
        moneyMinus(worksList[position].price)                // отнимаем деньги
        showMoney()
        repository.saveBoolean(ACCESS, worksList[position].name, true)
        var upgradeText = ""
        if (worksList[position].type == PROFESSION) {
            changeProfession(worksList[position].openestUpgrade)     //если профессия, то поменять
        } else {
            //если апгрейд
            repository.saveInt(STATS, worksList[position].type, repository.getInt(STATS, worksList[position].type,0)+1)
            if (worksList[position].openestUpgrade!="")upgradeText += "\nОткрыто\n\"${worksList[position].openestUpgrade}\""
        }
        showToast("Приобретено ${worksList[position].tittle} $upgradeText")       //в конце выводит сообщение о приобритении
        view.close() //закрыть фрагмент
        changeProfession()
    }

    //bad logic.
    private fun bad(accessText: String) {
        showToast(accessText)
    } //если проверки не пройдены, то выводит об этом сообщение
}