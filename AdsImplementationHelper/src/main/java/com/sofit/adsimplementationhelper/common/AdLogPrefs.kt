package com.sofit.adsimplementationhelper.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.sofit.adsimplementationhelper.models.AdLogModel

object AdLogPrefs{
    private val gson = Gson()

    fun saveLogs(myObject: AdLogModel, context: Context) {
        val preferenceName = AD_LOG_PREFS_NAME
         val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = gson.toJson(myObject)
        preference.edit().putString(ADS_LOG_PREFS_KEY, jsonString).apply()
    }

    fun getLogs(context: Context): AdLogModel? {
        val preferenceName = AD_LOG_PREFS_NAME
        val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = preference.getString(ADS_LOG_PREFS_KEY, null)
        return if (jsonString != null) {
            gson.fromJson(jsonString, AdLogModel::class.java)
        } else {
            null
        }
    }

}