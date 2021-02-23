package com.example.driverclicker.repository

interface RepositoryInt {
    fun getInt(fileName: String, key: String, defValue: Int): Int
    fun getString(fileName: String, key: String, defValue: String): String
    fun saveString(fileName: String, key: String, value: String)
    fun saveInt(fileName: String, key: String, value: Int)
    fun getBoolean(fileName: String, key: String, defValue: Boolean): Boolean
    fun saveBoolean(fileName: String, key: String, value: Boolean)
    fun getLong(fileName: String, key: String, defValue: Long): Long
    fun saveLong(fileName: String, key: String, value: Long)
    fun clearRepository()
}