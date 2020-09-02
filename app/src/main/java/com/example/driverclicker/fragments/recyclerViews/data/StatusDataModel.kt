package com.example.driverclicker.fragments.recyclerViews.data
//Data for RV in MenuOne/Two/Three()
data class StatusDataModel(val name: String="",
                           val tittle: String,
                           val desc: String,
                           val plusStat: Int,
                           val minusStat: Int,
                           val price: Int,
                           val action: String,
                           var achieved: Boolean = false
                           ) {
}