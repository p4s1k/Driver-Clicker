package com.example.driverclicker.fragments.menuFour

import com.example.driverclicker.basic.PresenterBasic
import com.example.driverclicker.enums.ProfessionsEnum
import com.example.driverclicker.fragments.recyclerViews.data.WorkDataModel
import com.example.driverclicker.enums.ProfessionsEnum.*
import com.example.driverclicker.enums.UpgradeEnum.*
import com.example.driverclicker.repository.RepositoryInt
import com.example.driverclicker.enums.ProfessionsEnum.valueOf

class MenuFourPresenter(val view: MenuFourView, override val repository: RepositoryInt) :
    PresenterBasic(repository, view) {
    private var worksList = ArrayList<WorkDataModel>()
    fun initData(): ArrayList<WorkDataModel> {
        worksList.add(
            WorkDataModel(
                HUNGER,
                Upgrade0.name,
                Upgrade0.tittle,
                Upgrade0.desc,
                Upgrade0.price,
                Upgrade0.level,
                false,
                Upgrade0.icon,
                Upgrade0.openingUpgrade
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Newspaper.name,
                Newspaper.tittle,
                Newspaper.desc,
                Newspaper.price,
                Newspaper.level,
                false,
                Newspaper.icon
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Post.name,
                Post.tittle,
                Post.desc,
                Post.price,
                Post.level,
                false,
                Post.icon
            )
        )
        worksList.add(
            WorkDataModel(
                HEALTH,
                Upgrade1.name,
                Upgrade1.tittle,
                Upgrade1.desc,
                Upgrade1.price,
                Upgrade1.level,
                false,
                Upgrade1.icon,
                Upgrade1.openingUpgrade
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Pizza.name,
                Pizza.tittle,
                Pizza.desc,
                Pizza.price,
                Pizza.level,
                false,
                Pizza.icon
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Sushi.name,
                Sushi.tittle,
                Sushi.desc,
                Sushi.price,
                Sushi.level,
                false,
                Sushi.icon
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                RailwayStation.name,
                RailwayStation.tittle,
                RailwayStation.desc,
                RailwayStation.price,
                RailwayStation.level,
                false,
                RailwayStation.icon
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Club.name,
                Club.tittle,
                Club.desc,
                Club.price,
                Club.level,
                false,
                Club.icon
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Hotel.name,
                Hotel.tittle,
                Hotel.desc,
                Hotel.price,
                Hotel.level,
                false,
                Hotel.icon
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Airport.name,
                Airport.tittle,
                Airport.desc,
                Airport.price,
                Airport.level,
                false,
                Airport.icon
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Gadgets.name,
                Gadgets.tittle,
                Gadgets.desc,
                Gadgets.price,
                Gadgets.level,
                false,
                Gadgets.icon
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Products.name,
                Products.tittle,
                Products.desc,
                Products.price,
                Products.level,
                false,
                Products.icon
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Wagon.name,
                Wagon.tittle,
                Wagon.desc,
                Wagon.price,
                Wagon.level,
                false,
                Wagon.icon
            )
        )
        worksList.add(
            WorkDataModel(
                PROFESSION,
                Truck.name,
                Truck.tittle,
                Truck.desc,
                Truck.price,
                Truck.level,
                false,
                Truck.icon
            )
        )
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
        if (position != 0) if (!worksList[position - 1].achieved) accessText += "${worksList[position - 1].tittle}\n"   //открыт ли апгрейд
        if (repository.getInt(SAVE, LVL, 0) >= level) {
            accessLevel = true
        } else accessText += "$level уровень\n" //подходит ли уровень
        if (repository.getInt(SAVE, MONEY, 0) >= price) {
            accessMoney = true
        } else accessText += "$price рублей"  //хватает ли денег

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
            changeProfession(worksList[position].name)     //если профессия, то поменять
        } else {
            //если апгрейд
            repository.saveInt(STATS, worksList[position].type, +1)
            upgradeText += "\nОткрыто\n\"${worksList[position].openestUpgrade}\""
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