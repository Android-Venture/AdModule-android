package com.sofit.adsimplementationhelper.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.admanager.models.AdRequestParamModel
import com.google.android.gms.ads.MobileAds
import com.sofit.adsimplementationhelper.ad_classes.AppOpenManager

object Utils {
    var BANNER_REQUEST :Int = 0
    var BANNER_IMPRESSION : Int = 0
    var NATIVE_REQUEST :Int = 0
    var NATIVE_IMPRESSION : Int = 0
    var INTERSTITIAL_REQUEST :Int = 0
    var INTERSTITIAL_IMPRESSION : Int = 0

    const val ADS_PARAM_PREFS_KEY = "ADS_PARAM"
    const val ADS_LOG_PREFS_KEY = "ADS_LOG"
    fun isInternetConnected(context: Context): Boolean {
        var isConnected = false
        val connectivityManager =context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            isConnected = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                activeNetworkInfo?.run {
                    isConnected = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return isConnected
    }

    fun adNetworkInitialize(application: Application,ad_params:AdRequestParamModel){


        if (isInternetConnected(application.applicationContext)){
            MobileAds.initialize(application.applicationContext){

                AppOpenManager(application,ad_params)

            }
        }


    }
}