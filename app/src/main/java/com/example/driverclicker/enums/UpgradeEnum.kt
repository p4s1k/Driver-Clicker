package com.example.driverclicker.enums

import com.example.driverclicker.R
import com.example.driverclicker.enums.StatsEnum.*
import com.example.driverclicker.enums.ProfessionsEnum.*
enum class UpgradeEnum (
    val type: String,
    val tittle: String,
    val desc: String,
    val price: Int,
    val level: Int,
    val icon: Int,
    val openingUpgrade: String=""
){
    Upgrade0("health","Телефон", "купить телефон", 0,0, R.drawable.item_icon, Health1.title),
    Upgrade1("hunger","Первые шаги", "Научиться ездить на велосипеде", 0,0, R.drawable.item_icon, Hunger1.title),
    Upgrade2("profession",Post.tittle,"Купить велосипед",Post.price,Post.level,R.drawable.item_icon, Post.name),
    Upgrade3("mood","Первые права","Получить права на скутер",0,0,R.drawable.item_icon, Mood1.title),
    Upgrade4("health","Беги, Форест","Купить новые кроссовки",0,0,R.drawable.item_icon, Health2.title),
    Upgrade5("profession",Pizza.tittle,"Купить скутер и развозить пиццу",Pizza.price,Pizza.level,R.drawable.item_icon,Pizza.name),
    Upgrade6("hunger","Права на авто","Получить права на авто",0,0,R.drawable.item_icon, Hunger2.title),
    Upgrade7("profession",Sushi.tittle,"Купить хетчбэк и развозить суши",Sushi.price,Sushi.level,R.drawable.item_icon, Sushi.name),
    Upgrade8("mood","Приставка","Купить домой игровую приставку",0,0,R.drawable.item_icon,Mood2.title ),
    Upgrade9("","Курсы такси","Пройти курсы и получить лицензию такси",0,0,R.drawable.item_icon),
    Upgrade10("profession",RailwayStation.tittle,RailwayStation.desc,RailwayStation.price,RailwayStation.level,R.drawable.item_icon, RailwayStation.name),
    Upgrade11("","Снять комнату", "Пора жить отдельно", 0,0,R.drawable.item_icon),
    Upgrade12("health","Абонемент", "Купить абонемент в фитнес зал", 0,0,R.drawable.item_icon, Health3.title),
    Upgrade13("profession",Club.tittle, Club.desc, Club.price,Club.level,R.drawable.item_icon, Club.name),
    Upgrade14("hunger","Контракт", "Договориться о работе на отель", 0,0,R.drawable.item_icon, Hunger3.title),
    Upgrade15("profession",Hotel.tittle, Hotel.desc, Hotel.price,Hotel.level,R.drawable.item_icon, Hotel.name),
    Upgrade16("mood","Жажда скорости", "Купить машину выходного дня", 0,0,R.drawable.item_icon, Mood3.title),
    Upgrade17("","Костюм", "Прикупить хороший костюм", 0,0,R.drawable.item_icon),
    Upgrade18("profession",Airport.tittle, Airport.desc, Airport.price,Airport.level,R.drawable.item_icon, Airport.name),
    Upgrade19("health","Санаторий", "Ознакомиться с отдыхом в санатории", 0,0,R.drawable.item_icon, Health4.title),
    Upgrade20("","Грузоперевозки", "Получить лицензию на грузоперевозки", 0,0,R.drawable.item_icon),
    Upgrade21("profession",Gadgets.tittle, Gadgets.desc, Gadgets.price,Gadgets.level,R.drawable.item_icon, Gadgets.name),
    Upgrade22("hunger","Ресторан", "Посетить впервые ресторан" , 0,0,R.drawable.item_icon, Hunger4.title),
    Upgrade23("","Снять кваритру", "Хватит жить с соседями, пора съехать" , 0,0,R.drawable.item_icon),
    Upgrade24("profession",Products.tittle, Products.desc , Products.price,Products.level,R.drawable.item_icon,Products.name),
    Upgrade25("mood","Девушка", "Одному быть грустно, надо завести девушку" , 0,0,R.drawable.item_icon, Mood4.title),
    Upgrade26("profession",Wagon.tittle,Wagon.desc,Wagon.price,Wagon.level,R.drawable.item_icon, Wagon.name),
    Upgrade27("hunger","Семья", "Предложить своей девушке жить вместе",0,0,R.drawable.item_icon, Hunger5.title),
    Upgrade28("mood","Свадьба", "Жениться",0,0,R.drawable.item_icon, Mood5.title),
    Upgrade29("health","Курорт", "Впервые слетать на курорт",0,0,R.drawable.item_icon, Health5.title),
    Upgrade30("","Опасные грузы", "Получить разрешение перевозить опасные грузы",0,0,R.drawable.item_icon),
    Upgrade31("profession",Truck.tittle, Truck.desc,Truck.price,Truck.level,R.drawable.item_icon, Truck.name)
}
