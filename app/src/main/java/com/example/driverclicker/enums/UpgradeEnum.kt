package com.example.driverclicker.enums

import com.example.driverclicker.R
import com.example.driverclicker.enums.StatsEnum.*
import com.example.driverclicker.enums.ProfessionsEnum.*
enum class UpgradeEnum (
    val type: String,
    val tittle: Int,
    val desc: Int,
    val price: Int,
    val level: Int,
    val icon: Int,
    val openingUpgrade: String=""
){
    Upgrade0("health",R.string.up0_tittle, R.string.up0_desc, 150,1, R.drawable.icon_phone, Health1.name),
    Upgrade1("hunger",R.string.up1_tittle, R.string.up1_desc, 0,2, R.drawable.icon_bicycle, Hunger1.name),
    Upgrade2("profession", Post.tittle,R.string.up2_desc,Post.price,Post.level,R.drawable.icon_post, Post.name),
    Upgrade3("mood",R.string.up3_tittle,R.string.up3_desc,1000,6,R.drawable.icon_scooter_license, Mood1.name),
    Upgrade4("health",R.string.up4_tittle,R.string.up4_desc,200,8,R.drawable.icon_sneakers, Health2.name),
    Upgrade5("profession", Pizza.tittle,R.string.up5_desc,Pizza.price,Pizza.level,R.drawable.icon_scooter_buy,Pizza.name),
    Upgrade6("hunger",R.string.up6_tittle,R.string.up6_desc,1500,12,R.drawable.icon_driver_license, Hunger2.name),
    Upgrade7("profession", Sushi.tittle,R.string.up7_desc,Sushi.price,Sushi.level,R.drawable.icon_civic_buy, Sushi.name),
    Upgrade8("mood",R.string.up8_tittle,R.string.up8_desc,2000,16,R.drawable.icon_playstation,Mood2.name ),
    Upgrade9("",R.string.up9_tittle,R.string.up9_desc,7000,18,R.drawable.icon_taxi_license),
    Upgrade10("profession",RailwayStation.tittle,RailwayStation.desc,RailwayStation.price,RailwayStation.level,R.drawable.icon_railway_station, RailwayStation.name),
    Upgrade11("",R.string.up11_tittle, R.string.up11_desc, 10000,22,R.drawable.icon_rent_room),
    Upgrade12("health",R.string.up12_tittle, R.string.up12_desc, 5000,24,R.drawable.icon_fitness, Health3.name),
    Upgrade13("profession",Club.tittle, Club.desc, Club.price,Club.level,R.drawable.icon_club, Club.name),
    Upgrade14("hunger",R.string.up14_tittle, R.string.up14_desc, 0,28,R.drawable.icon_hotel, Hunger3.name),
    Upgrade15("profession",Hotel.tittle, Hotel.desc, Hotel.price,Hotel.level,R.drawable.icon_miniven, Hotel.name),
    Upgrade16("mood",R.string.up16_title, R.string.up16_desc, 40000,32,R.drawable.icon_racing, Mood3.name),
    Upgrade17("",R.string.up17_tittle, R.string.up17_desc, 10000,34,R.drawable.icon_smoking),
    Upgrade18("profession",Airport.tittle, Airport.desc, Airport.price,Airport.level,R.drawable.icon_airport, Airport.name),
    Upgrade19("health",R.string.up19_tittle, R.string.up19_desc, 100000,38,R.drawable.icon_sanatorium, Health4.name),
    Upgrade20("",R.string.up20_tittle, R.string.up20_desc, 50000,40,R.drawable.icon_cargo),
    Upgrade21("profession",Gadgets.tittle, Gadgets.desc, Gadgets.price,Gadgets.level,R.drawable.icon_gadgets, Gadgets.name),
    Upgrade22("hunger",R.string.up22_tittle, R.string.up22_desc , 10000,44,R.drawable.icon_restaurant, Hunger4.name),
    Upgrade23("",R.string.up23_tittle, R.string.up23_desc, 50000,46,R.drawable.icon_flat),
    Upgrade24("profession",Products.tittle, Products.desc , Products.price,Products.level,R.drawable.icon_prods,Products.name),
    Upgrade25("mood",R.string.up25_tittle, R.string.up25_desc , 69,50,R.drawable.item_girlfriend, Mood4.name),
    Upgrade26("profession",Wagon.tittle,Wagon.desc,Wagon.price,Wagon.level,R.drawable.item_build, Wagon.name),
    Upgrade27("hunger",R.string.up27_tittle, R.string.up27_desc,500000,54,R.drawable.icon_family, Hunger5.name),
    Upgrade28("mood",R.string.up28_tittle, R.string.up28_desc,1000000,56,R.drawable.icon_wife, Mood5.name),
    Upgrade29("health",R.string.up29_tittle, R.string.up29_desc,100000,58,R.drawable.icon_holiday, Health5.name),
    Upgrade30("",R.string.up30_tittle, R.string.up30_desc,75000,60,R.drawable.item_fire_license),
    Upgrade31("profession",Truck.tittle, Truck.desc,Truck.price,Truck.level,R.drawable.item_truck_license, Truck.name)
}
