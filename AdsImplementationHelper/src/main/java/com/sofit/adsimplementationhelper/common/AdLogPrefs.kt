package com.sofit.adsimplementationhelper.common

import android.content.Context
import android.content.SharedPreferences
import com.example.admanager.models.AdLogModel
import com.google.gson.Gson

object AdLogPrefs{

    //TODO : Remove empty spaces like this and beatify your cade so reviewer can go through easily




    private val gson = Gson()

    fun saveLogs(myObject: AdLogModel,context: Context) {

        //TODO : Static string are not allowed, create a Constant file and shift these there.
        val preferenceName = "AD_LOG_PREFS"
         val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = gson.toJson(myObject)
        preference.edit().putString(Utils.ADS_LOG_PREFS_KEY, jsonString).apply()
    }

    fun getLogs(context: Context): AdLogModel? {
        //TODO : Static string are not allowed, create a Constant file and shift these there.
        val preferenceName = "AD_LOG_PREFS"
        val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = preference.getString(Utils.ADS_LOG_PREFS_KEY, null)
        return if (jsonString != null) {
            gson.fromJson(jsonString, AdLogModel::class.java)
        } else {
            null
        }
    }

}