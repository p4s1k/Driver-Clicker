package com.example.driverclicker.repository

import android.content.Context

object LocalRepository : RepositoryApplicationClass(), RepositoryInt {

    fun init(context: Context) {
        preferencesSave = context.getSharedPreferences(SAVE, Context.MODE_PRIVATE)
        preferencesStats = context.getSharedPreferences(STATS, Context.MODE_PRIVATE)
        preferencesAccess = context.getSharedPreferences(ACCESS, Context.MODE_PRIVATE)
    }

    override fun getInt(fileName: String, key: String, defValue: Int): Int {
        var result = defValue
        when (fileName) {
            SAVE -> result = preferencesSave.getInt(key, defValue)
            STATS -> result = preferencesStats.getInt(key, defValue)
            ACCESS -> result = preferencesAccess.getInt(key, defValue)
        }
        return result
    }

    override fun getString(fileName: String, key: String, defValue: String): String {
        var result = defValue
        when (fileName) {
            SAVE -> result = preferencesSave.getString(key, defValue).toString()
            STATS -> result = preferencesStats.getString(key, defValue).toString()
            ACCESS -> result = preferencesAccess.getString(key, defValue).toString()
        }
        return result
    }

    override fun saveString(fileName: String, key: String, value: String) {
        when (fileName) {
            SAVE -> preferencesSave.edit().putString(key, value).commit()
            STATS -> preferencesStats.edit().putString(key, value).commit()
            ACCESS -> preferencesAccess.edit().putString(key, value).commit()
        }
    }

    override fun saveInt(fileName: String, key: String, value: Int) {
        when (fileName) {
            SAVE -> preferencesSave.edit().putInt(key, value).commit()
            STATS -> preferencesStats.edit().putInt(key, value).commit()
            ACCESS -> preferencesAccess.edit().putInt(key, value).commit()
        }
    }

    override fun getBoolean(fileName: String, key: String, defValue: Boolean): Boolean {
        var result = defValue
        when (fileName) {
            SAVE -> result = preferencesSave.getBoolean(key, defValue)
            STATS -> result = preferencesStats.getBoolean(key, defValue)
            ACCESS -> result = preferencesAccess.getBoolean(key, defValue)
        }
        return result
    }

    override fun saveBoolean(fileName: String, key: String, value: Boolean) {
        when (fileName) {
            SAVE -> preferencesSave.edit().putBoolean(key, value).commit()
            STATS -> preferencesStats.edit().putBoolean(key, value).commit()
            ACCESS -> preferencesAccess.edit().putBoolean(key, value).commit()
        }
    }

    override fun getLong(fileName: String, key: String, defValue: Long): Long {
        var result = defValue
        when(fileName){
            SAVE -> result = preferencesSave.getLong(key, defValue)
            STATS -> result = preferencesStats.getLong(key, defValue)
            ACCESS -> result = preferencesAccess.getLong(key, defValue)
        }
        return result
    }

    override fun saveLong(fileName: String, key: String, value: Long) {
        when (fileName) {
            SAVE -> preferencesSave.edit().putLong(key, value).commit()
            STATS -> preferencesStats.edit().putLong(key, value).commit()
            ACCESS -> preferencesAccess.edit().putLong(key, value).commit()
        }
    }


    override fun clearRepository() {
        preferencesSave.edit().clear().apply()
        preferencesStats.edit().clear().apply()
        preferencesAccess.edit().clear().apply()
    }
}
