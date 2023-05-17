package com.sofit.adsimplementationhelper.common

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.MobileAds
import com.sofit.adsimplementationhelper.ad_classes.AppOpenManager

object Utils {
    var adLogs:ArrayList<String> = ArrayList()
    var ShowAppOpen = true
    var interstitialAdCapping = 0

    @RequiresApi(Build.VERSION_CODES.M)
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
                val networkCapabilities = getNetworkCapabilities(activeNetwork)
                isConnected = networkCapabilities?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            }
        }
        return isConnected
    }



    @RequiresApi(Build.VERSION_CODES.M)
    fun adNetworkInitialize(application: Application){
         if (isInternetConnected(application.applicationContext)){
             MobileAds.initialize(application.applicationContext){
                 AppOpenManager(application)
             }
         }
    }
    fun isAppDownloadedFromPlayStore(context: Context): Boolean {
        val installer = context.packageManager.getInstallerPackageName(context.packageName)
        return "com.android.vending" == installer
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun getAdSize(activity: Activity, view: View): AdSize {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        val display = activity.display
        val outMetrics = DisplayMetrics()
        display?.getRealMetrics(outMetrics)

        val density = outMetrics.density

        var adWidthPixels = view.width.toFloat()
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }

        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
    }

}