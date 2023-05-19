package com.sofit.adsimplementationhelper.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.sofit.adsimplementationhelper.models.AdRequestParamModel

object AdParamsPrefs {

    private val gson = Gson()

    fun saveParams(params: AdRequestParamModel, context: Context) {
        val preferenceName = AD_PARAMS_PREFS_NAME
        val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = gson.toJson(params)
        preference.edit().putString(ADS_PARAM_PREFS_KEY, jsonString).apply()
    }

    fun getParams(context: Context): AdRequestParamModel? {
        val preferenceName = AD_PARAMS_PREFS_NAME
        val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = preference.getString(ADS_PARAM_PREFS_KEY, null)
        return if (jsonString != null) {
            gson.fromJson(jsonString, AdRequestParamModel::class.java)
        } else {
            null
        }
    }

}