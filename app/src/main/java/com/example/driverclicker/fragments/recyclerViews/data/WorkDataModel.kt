package com.example.driverclicker.fragments.recyclerViews.data

//Data for RV in MenuFour()
data class WorkDataModel (val type: String,
                          val name: String,
                          val tittle: String,
                          val desc: String,
                          val price: Int,
                          val level: Int,
                          var achieved: Boolean,
                          val icon: Int,
                          val openestUpgrade: String = ""
)