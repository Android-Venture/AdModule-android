package com.sofit.adsimplementationhelper.ad_classes

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback
import com.sofit.adsimplementationhelper.common.AdParamsPrefs

class AppOpenManager(private val myApplication: Application) :
    ActivityLifecycleCallbacks, LifecycleObserver {
    private var appOpenAd: AppOpenAd? = null
    var loadCallback: AppOpenAdLoadCallback? = null
    var currentActivity: Activity? = null

    /**
     * Constructor
     */
    init {
        myApplication.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    /**
     * Request an ad
     */
    fun fetchAd() {
        // Have unused ad, no need to fetch another.
        if (isAdAvailable) {
            return
        }
        loadCallback = object : AppOpenAdLoadCallback() {
            override fun onAdLoaded(appOpenAd: AppOpenAd) {
                super.onAdLoaded(appOpenAd)
                this@AppOpenManager.appOpenAd = appOpenAd
                Log.d("APP_OPEN", "onAdLoaded: ")
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                super.onAdFailedToLoad(loadAdError)
                Log.d("APP_OPEN", "onfailed $loadAdError")
            }
        }
        val request = adRequest

        if ((AdParamsPrefs.getParams(myApplication.applicationContext)?.appOpenAdStatus ==true)){
            AppOpenAd.load(
                myApplication,
                AdParamsPrefs.getParams(myApplication.applicationContext)?.appOpenAdId!!,
                request,
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                loadCallback as AppOpenAdLoadCallback
            )
        }

    }
    /**
     * Shows the ad if one isn't already showing.
     */
    fun showAdIfAvailable() {
        // Only show ad if there is not already an app open ad currently showing
        // and an ad is available.
        if (AdParamsPrefs.getParams(myApplication.applicationContext)?.appOpenAdStatus == true){
            if (!isShowingAd && isAdAvailable) {
                Log.d(LOG_TAG, "Will show ad.")
                val fullScreenContentCallback: FullScreenContentCallback =
                    object : FullScreenContentCallback() {
                        override fun onAdDismissedFullScreenContent() {
                            // Set the reference to null so isAdAvailable() returns false.
                            appOpenAd = null
                            isShowingAd = false
                            fetchAd()
                        }
                        override fun onAdFailedToShowFullScreenContent(adError: AdError) {}
                        override fun onAdShowedFullScreenContent() {
                            isShowingAd = true
                        }
                    }
                appOpenAd!!.show(currentActivity!!)
                appOpenAd!!.fullScreenContentCallback = fullScreenContentCallback
            }

            else {

                fetchAd()
            }
        }

    }

    /**
     * Creates and returns ad request.
     */
    private val adRequest: AdRequest
        get() = AdRequest.Builder().build()

    /**
     * Utility method that checks if ad exists and can be shown.
     */
    val isAdAvailable: Boolean
        get() = appOpenAd != null

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }

    /** LifecycleObserver methods  */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        showAdIfAvailable()
    }

    companion object {
        const val LOG_TAG = "AppOpenManager"
        private var isShowingAd = false
    }
}