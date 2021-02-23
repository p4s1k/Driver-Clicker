package com.example.driverclicker.enums

import com.example.driverclicker.R

enum class ProfessionsEnum(
    val tittle: Int,
    val desc: Int,
    val price: Int,
    val level: Int,
    val icon: Int,
    val background: Int,
    val imageRes: Int,
    val income: Int,
    val clickMin: Int,
    val clickMax: Int
)
{
    Newspaper(R.string.newspaper_tittle, R.string.newspaper_desc, 0, 0, R.drawable.item_icon_newspaper, R.drawable.background_newspaper, R.drawable.newspaper, 5, -20, 0),
    Post(R.string.post_tittle, R.string.post_desc, 500, 3, R.drawable.item_icon_post, R.drawable.background_post, R.drawable.bicycle, 15, -10,0),
    Pizza(R.string.pizza_tittle, R.string.pizza_desc, 5000, 10, R.drawable.item_icon_license,R.drawable.background_pizza, R.drawable.scooter, 45, -10, 0),
    Sushi(R.string.sushi_tittle, R.string.sushi_desc, 25000, 14, R.drawable.item_icon_sushi,R.drawable.background_sushi, R.drawable.civic, 100, -10, 0),
    RailwayStation(R.string.RailwayStation_tittle, R.string.RailwayStation_desc, 60000, 20, R.drawable.item_icon,R.drawable.background_railway, R.drawable.railway, 230, -10, 0),
    Club(R.string.club_tittle, R.string.club_desc, 200000, 26, R.drawable.item_icon,R.drawable.background_club, R.drawable.club, 435, -10, 0),
    Hotel(R.string.hotel_tittle, R.string.hotel_desc, 300000, 30, R.drawable.item_icon,R.drawable.background_hotel, R.drawable.hotel, 565, -10, 0),
    Airport(R.string.airport_tittle, R.string.airport_desc, 600000, 36, R.drawable.item_icon,R.drawable.background_airport, R.drawable.airport, 1000, -10, 0),
    Gadgets(R.string.gadgets_tittle, R.string.gadgets_desc, 800000, 42, R.drawable.item_icon,R.drawable.background_gadgets, R.drawable.gadgets, 700, -10, 0),
    Products(R.string.products_tittle, R.string.products_desc, 1300000, 48, R.drawable.item_icon, R.drawable.background_prods, R.drawable.products, 955, -10, 0),
    Wagon(R.string.wagon_tittle, R.string.wagon_desc, 1600000, 52, R.drawable.item_icon,R.drawable.background_wagon,R.drawable.wagon, 1345, -10, 0),
    Truck(R.string.truck_tittle, R.string.truck_tittle, 15000000, 78, R.drawable.item_icon,R.drawable.background_truck , R.drawable.truck, 1500, -10, 0)
}