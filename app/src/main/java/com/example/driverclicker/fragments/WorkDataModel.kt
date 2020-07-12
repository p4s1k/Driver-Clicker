package com.example.driverclicker.fragments

data class WorkDataModel (val type: String,
                          val tittle: String,
                          val desc: String,
                          val vehicle: Int,
                          val price: Int,
                          val level: Int,
                          var access: Boolean,
                          val icon: Int
)