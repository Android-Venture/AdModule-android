package com.sofit.adsimplementationhelper.common

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import com.example.admanager.models.AdRequestParamModel
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.MobileAds
import com.sofit.adsimplementationhelper.BuildConfig
import com.sofit.adsimplementationhelper.ad_classes.AppOpenManager

//TODO: This file contain 1 error 11 warnings and 1 minor error make sure to remove these type of errors before commit
object Utils {
    var BANNER_REQUEST :Int = 0
    var BANNER_IMPRESSION : Int = 0
    var NATIVE_REQUEST :Int = 0
    var NATIVE_IMPRESSION : Int = 0
    var INTERSTITIAL_REQUEST :Int = 0
    var INTERSTITIAL_IMPRESSION : Int = 0

    //TODO : Static string are not allowed, create a Constant file and shift these there.
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

    fun adNetworkInitialize(application: Application){


         if (isInternetConnected(application.applicationContext) && isAppDownloadedFromPlayStore(application.applicationContext)){
             MobileAds.initialize(application.applicationContext){
                 AppOpenManager(application)
             }
         }
        //TODO : Remove empty spaces like this and beatify your cade so reviewer can go through easily




    }

    fun isAppDownloadedFromPlayStore(context: Context): Boolean {
        val installer = context.packageManager.getInstallerPackageName(context.packageName)
        //TODO : Static string are not allowed, create a Constant file and shift these there.
        return "com.android.vending" == installer
    }


    fun getAdSize(activity: Activity, view: View): AdSize? {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        //TODO: Avoid deprecations
        val display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density

        var adWidthPixels = view.width.toFloat()
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }

        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
    }
}