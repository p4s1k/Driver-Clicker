package com.example.driverclicker.fragments.recyclerViews.data
//Data for RV in MenuOne/Two/Three()
data class StatusDataModel(val name: String="",
                           val tittle: Int,
                           val desc: String,
                           val plusStat: Int,
                           val minusStat: Int,
                           val price: Int,
                           val action: Int,
                           var achieved: Boolean = false,
                           val icon : Int
                           ) {
}