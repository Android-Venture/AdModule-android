package com.sofit.adsimplementationhelper.common

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.MobileAds
import com.sofit.adsimplementationhelper.ad_classes.AppOpenManager

object Utils {
    var adLogs:ArrayList<String> = ArrayList()
    var ShowAppOpen = true
    var interstitialAdCapping = 0




    fun isInternetConnected(context: Context): Boolean {
        var isConnected = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
            try {
                val method = connectivityManager.javaClass.getMethod("getNetworkInfo", Int::class.javaPrimitiveType)
                val networkInfo = method.invoke(connectivityManager, ConnectivityManager.TYPE_WIFI) as NetworkInfo?
                isConnected = networkInfo?.isConnected ?: false
                if (!isConnected) {
                    val networkInfoMobile = method.invoke(connectivityManager, ConnectivityManager.TYPE_MOBILE) as NetworkInfo?
                    isConnected = networkInfoMobile?.isConnected ?: false
                }
            } catch (e: Exception) {
                // Handle the error when the method is not available on the device
                e.printStackTrace()
            }
        }
        return isConnected
    }

    fun adNetworkInitialize(application: Application){

             MobileAds.initialize(application.applicationContext){
                 if (isInternetConnected(application.applicationContext)) {
                     AppOpenManager(application)
                 }
             }

    }
    fun isAppDownloadedFromPlayStore(context: Context): Boolean {
        val installer = context.packageManager.getInstallerPackageName(context.packageName)
        return "com.android.vending" == installer
    }



//    fun getAdSize(activity: Activity, view: View): AdSize {
//        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
//        val display = activity.display
//        val outMetrics = DisplayMetrics()
//        display?.getRealMetrics(outMetrics)
//
//        val density = outMetrics.density
//
//        var adWidthPixels = view.width.toFloat()
//        if (adWidthPixels == 0f) {
//            adWidthPixels = outMetrics.widthPixels.toFloat()
//        }
//
//        val adWidth = (adWidthPixels / density).toInt()
//        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
//    }


    fun getAdSize(activity: Activity, view: View): AdSize {
        val display: Display?
        try {
            val method = Activity::class.java.getMethod("getDisplay")
            display = method.invoke(activity) as Display
        } catch (e: Exception) {
            e.printStackTrace()
            return AdSize.BANNER // Fallback if unable to access display
        }

        val outMetrics = DisplayMetrics()
        val getRealMetricsMethod = Display::class.java.getMethod("getRealMetrics", DisplayMetrics::class.java)
        getRealMetricsMethod.invoke(display, outMetrics)

        val density = outMetrics.density

        var adWidthPixels = view.width.toFloat()
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }

        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
    }

}