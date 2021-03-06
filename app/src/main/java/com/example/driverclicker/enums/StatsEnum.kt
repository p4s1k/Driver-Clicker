package com.example.driverclicker.enums

import com.example.driverclicker.R

enum class StatsEnum(
    val title: Int,
    val desc: String,
    val minusHealth: Int,
    val plusHealth: Int,
    val minusHunger: Int,
    val plusHunger: Int,
    val minusMood: Int,
    val plusMood: Int,
    val price: Int,
    val action: Int, //добавить иконки
    val icon: Int,
) {
    Health0(R.string.health0_tittle,
        "Прилечь и поспать",
        1,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.health0_action,
        R.drawable.icon_sleep),
    Health1(R.string.health1_tittle,
        "Почитать книгу о чудотворных травах",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.health1_action,
        R.drawable.icon_relax),
    Health2(R.string.health2_tittle,
        "Пробежаться по стадиону",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.health2_action,
        R.drawable.icon_run),
    Health3(R.string.health3_tittle,
        "Позанимать в зале с тренером",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.health3_action,
        R.drawable.icon_fitness),
    Health4(R.string.health4_tittle,
        "Съездить в санаторий на вызодные",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.health4_action,
        R.drawable.icon_sanatorium),
    Health5(R.string.health5_tittle,
        "Слетать на отдых в теплые страны",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.health5_action,
        R.drawable.icon_holiday),

    Hunger0(R.string.hunger0_tittle,
        "Съесть лапшу быстрого приготовления",
        -5,
        0,
        1,
        15,
        -5,
        0,
        0,
        R.string.hunger0_action,
        R.drawable.icon_soup),
    Hunger1(R.string.hunger1_tittle,
        "Съесть местный кебаб",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.hunger1_action,
        R.drawable.icon_kebab),
    Hunger2(R.string.hunger2_tittle,
        "Съездить на шашлык с друзьями",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.hunger2_action,
        R.drawable.icon_bbq),
    Hunger3(R.string.hunger3_tittle,
        "Сходить в столовую при отеле",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.hunger3_action,
        R.drawable.icon_dining_room),
    Hunger4(R.string.hunger4_tittle,
        "Посетить дорогой ресторан",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.hunger4_action,
        R.drawable.icon_restaurant),
    Hunger5(R.string.hunger5_tittle,
        "Съездить домой, пусть жена накормит",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.hunger5_action,
        R.drawable.icon_diner),

    Mood0(R.string.mood0_tittle,
        "Выпить бутылочку холдоного",
        -5,
        0,
        -5,
        0,
        1,
        15,
        0,
        R.string.mood0_action,
        R.drawable.icon_beer),
    Mood1(R.string.mood1_tittle,
        "Посмотреть фильм по подписке",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.mood1_action,
        R.drawable.icon_cinema),
    Mood2(R.string.mood2_tittle,
        "Поиграть час другой на приставке",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.mood2_action,
        R.drawable.icon_playstation),
    Mood3(R.string.mood3_tittle,
        "Съездить на нелегальные гонки",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.mood3_action,
        R.drawable.icon_racing),
    Mood4(R.string.mood4_tittle,
        "Прогуляться по вечернему городу с любимой девушкой",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.mood4_action,
        R.drawable.icon_meet),
    Mood5(R.string.mood5_tittle,
        "Исполнить супружиский долг",
        -5,
        15,
        -5,
        0,
        -5,
        0,
        0,
        R.string.mood5_action,
        R.drawable.icon_wife)
}