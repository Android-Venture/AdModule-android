package com.example.admanager.common

import android.content.Context
import android.content.SharedPreferences
import com.example.admanager.models.AdLogModel
import com.google.gson.Gson

object AdLogPrefs{





    private val gson = Gson()

    fun saveLogs(myObject: AdLogModel,context: Context) {
        val preferenceName = "AD_LOG_PREFS"
         val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = gson.toJson(myObject)
        preference.edit().putString("ADS_LOG", jsonString).apply()
    }

    fun getLogs(context: Context): AdLogModel? {
        val preferenceName = "AD_LOG_PREFS"
        val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = preference.getString("ADS_LOG", null)
        return if (jsonString != null) {
            gson.fromJson(jsonString, AdLogModel::class.java)
        } else {
            null
        }
    }

}