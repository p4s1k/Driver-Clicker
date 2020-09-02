package com.example.driverclicker.enums

import com.example.driverclicker.R

enum class ProfessionsEnum(
    val tittle: String,
    val desc: String,
    val price: Int,
    val level: Int,
    val icon: Int,
    val background: Int,
    val imageRes: Int,
    val income: Int

) {
    Newspaper("Разнозчик газет", "телега", 0, 0, R.drawable.item_icon_newspaper, R.drawable.background_newspaper, R.drawable.newspaper, 1),
    Post("Почтальон", "велик", 0, 0, R.drawable.item_icon_post, R.drawable.background_post, R.drawable.bicycle, 4),
    Pizza("Доставщик пиццы", "скутер", 0, 0, R.drawable.item_icon_license,R.drawable.background_pizza, R.drawable.scooter, 10),
    Sushi("Доставщик суши", "сивик", 0, 0, R.drawable.item_icon_sushi,R.drawable.background_sushi, R.drawable.civic, 20),
    RailwayStation("Бобмила на вокзале", "вокзал", 0, 0, R.drawable.item_icon,R.drawable.background_railway, R.drawable.railway, 40),
    Club("Бомбила у клуба", "клуб", 0, 0, R.drawable.item_icon,R.drawable.background_club, R.drawable.club, 60),
    Hotel("Бомбить у отеля", "отель", 0, 0, R.drawable.item_icon,R.drawable.background_hotel, R.drawable.hotel, 80),
    Airport("Бомбить в аэро", "аэропорт", 0, 0, R.drawable.item_icon,R.drawable.background_airport, R.drawable.airport, 100),
    Gadgets("Возить гаджеты", "Гаджеты", 0, 0, R.drawable.item_icon,R.drawable.background_gadgets, R.drawable.gadgets, 110),
    Products("Возить продукты", "Продукты", 0, 0, R.drawable.item_icon, R.drawable.background_prods, R.drawable.products, 140),
    Wagon("Возить стройматерилы", "Стройматериалы", 0, 0, R.drawable.item_icon,R.drawable.background_wagon,R.drawable.wagon, 150),
    Truck("Возить нефть", "Нефть", 0, 0, R.drawable.item_icon,R.drawable.background_truck , R.drawable.truck, 200)
}