package com.sofit.adsimplementationhelper.ad_classes

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.example.admanager.models.AdLogModel
import com.example.admanager.models.AdRequestParamModel
import com.google.android.gms.ads.*
import com.sofit.adsimplementationhelper.common.AdLogPrefs
import com.sofit.adsimplementationhelper.common.Utils
import com.sofit.adsimplementationhelper.common.Utils.isInternetConnected

object AdmobBanner {

    //TODO : Remove empty spaces like this and beatify your cade so reviewer can go through easily


    fun showAdmobBanner(
        admob_banner: FrameLayout,
        activity: Activity,
        requestParams:AdRequestParamModel, callback:com.sofit.adsimplementationhelper.common.AdLoadCallback) {

        //TODO : Avoid using double bang operator as they cause crashes, instead handle the null safety
        if (isInternetConnected(activity)  && requestParams.banner_ad_status!! )  {

            val adView = AdView(activity)
            Utils.BANNER_REQUEST++
            AdLogPrefs.saveLogs(
                AdLogModel(
                    Utils.BANNER_REQUEST,
                    Utils.BANNER_IMPRESSION,
                    Utils.NATIVE_REQUEST,
                    Utils.NATIVE_IMPRESSION,
                    Utils.INTERSTITIAL_REQUEST,
                    Utils.INTERSTITIAL_IMPRESSION
                ), activity
            )

            Utils.getAdSize(activity, admob_banner)?.let { adView.setAdSize(it) }
            adView.adUnitId = requestParams.banner_id!!
            admob_banner.addView(adView)
            val adRequest = AdRequest.Builder().build()
            adView.adListener = object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)

                    callback.onFailed()
                    //TODO: Remove logs before commit
                    Log.d("BANNER", "onAdFailedToLoad:$loadAdError")
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    callback.onLoaded()
                    //TODO: Remove useless commented code
//                    admob_banner.visibility = View.VISIBLE
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Utils.BANNER_IMPRESSION++
                    AdLogPrefs.saveLogs(
                        AdLogModel(
                            Utils.BANNER_REQUEST,
                            Utils.BANNER_IMPRESSION,
                            Utils.NATIVE_REQUEST,
                            Utils.NATIVE_IMPRESSION,
                            Utils.INTERSTITIAL_REQUEST,
                            Utils.INTERSTITIAL_IMPRESSION
                        ), activity
                    )

                }

            }
            adView.loadAd(adRequest)
        }
        //TODO : Remove empty spaces like this and beatify your cade so reviewer can go through easily


    }
}