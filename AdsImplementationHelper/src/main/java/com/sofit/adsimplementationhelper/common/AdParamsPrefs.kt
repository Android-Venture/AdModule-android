package com.sofit.adsimplementationhelper.common

import android.content.Context
import android.content.SharedPreferences
import com.example.admanager.models.AdLogModel
import com.example.admanager.models.AdRequestParamModel
import com.google.gson.Gson

object AdParamsPrefs {


    private val gson = Gson()

    //TODO : Why this function is not been used and get params is been used ?
    fun saveParams(params: AdRequestParamModel, context: Context) {

        //TODO : Static string are not allowed, create a Constant file and shift these there.
        val preferenceName = "AD_PARAMS_PREFS"
        val preference: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val jsonString = gson.toJson(params)
        preference.edit().putString(Utils.ADS_PARAM_PREFS_KEY, jsonString).apply()
    }

    fun getParams(context: Context): AdRequestParamModel? {

        //TODO : Static string are not allowed, create a Constant file and shift these there.
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