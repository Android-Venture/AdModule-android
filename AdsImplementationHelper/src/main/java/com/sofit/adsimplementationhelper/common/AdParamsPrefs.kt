package com.sofit.adsimplementationhelper.common

import android.content.Context
import android.content.SharedPreferences
import com.example.admanager.models.AdLogModel
import com.example.admanager.models.AdRequestParamModel
import com.google.gson.Gson

object AdParamsPrefs {


    private val gson = Gson()

    fun saveParams(params: AdRequestParamModel, context: Context) {
        val preferenceName = "AD_PARAMS_PREFS"
        val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = gson.toJson(params)
        preference.edit().putString(Utils.ADS_PARAM_PREFS_KEY, jsonString).apply()
    }

    fun getParams(context: Context): AdRequestParamModel? {
        val preferenceName = "AD_PARAMS_PREFS"
        val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = preference.getString(Utils.ADS_PARAM_PREFS_KEY, null)
        return if (jsonString != null) {
            gson.fromJson(jsonString, AdRequestParamModel::class.java)
        } else {
            null
        }
    }

}