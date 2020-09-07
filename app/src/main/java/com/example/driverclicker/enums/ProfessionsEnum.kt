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
    Post("Почтальон", "Устроиться курьером на почту", 0, 0, R.drawable.item_icon_post, R.drawable.background_post, R.drawable.bicycle, 4),
    Pizza("Пиццерия", "Устроиться курьером в пиццерию", 0, 0, R.drawable.item_icon_license,R.drawable.background_pizza, R.drawable.scooter, 10),
    Sushi("Суши", "Доставка суши", 0, 0, R.drawable.item_icon_sushi,R.drawable.background_sushi, R.drawable.civic, 20),
    RailwayStation("Вокзал", "Таксовать на вокзале", 0, 0, R.drawable.item_icon,R.drawable.background_railway, R.drawable.railway, 40),
    Club("Клубная жизнь", "Таксовать у ночного клуба", 0, 0, R.drawable.item_icon,R.drawable.background_club, R.drawable.club, 60),
    Hotel("Отель", "Возить клиентов отеля", 0, 0, R.drawable.item_icon,R.drawable.background_hotel, R.drawable.hotel, 80),
    Airport("Аэропорт", "Возить бизнес-клиентов из аэропорта", 0, 0, R.drawable.item_icon,R.drawable.background_airport, R.drawable.airport, 100),
    Gadgets("Гаджеты", "Возить бытовую технику и гаджеты", 0, 0, R.drawable.item_icon,R.drawable.background_gadgets, R.drawable.gadgets, 110),
    Products("Продукты", "Возить продукты хлебзавода по магазинам", 0, 0, R.drawable.item_icon, R.drawable.background_prods, R.drawable.products, 140),
    Wagon("Стройка", "Возить товар со склада магазина стройматериалов", 0, 0, R.drawable.item_icon,R.drawable.background_wagon,R.drawable.wagon, 150),
    Truck("Топливо", "Возить топливо на автозаправочные станции", 0, 0, R.drawable.item_icon,R.drawable.background_truck , R.drawable.truck, 200)
}