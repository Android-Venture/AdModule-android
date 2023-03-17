package com.sofit.adsimplementationhelper.ad_classes

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.example.admanager.models.AdLogModel
import com.google.android.gms.ads.*
import com.sofit.adsimplementationhelper.common.AdLogPrefs
import com.sofit.adsimplementationhelper.common.Utils
import com.sofit.adsimplementationhelper.common.Utils.isInternetConnected

object AdmobBanner {


    var counter = 0

    fun showAdmobBanner(
        admob_banner: FrameLayout,
        context: Context
    ) {

        if (!isInternetConnected(context)) {
            return
        } else if (counter < 20
        ) {
            counter++
            val adView = AdView(context)
            Utils.BANNER_REQUEST++
            AdLogPrefs.saveLogs(
                AdLogModel(
                    Utils.BANNER_REQUEST,
                    Utils.BANNER_IMPRESSION,
                    Utils.NATIVE_REQUEST,
                    Utils.NATIVE_IMPRESSION,
                    Utils.INTERSTITIAL_REQUEST,
                    Utils.INTERSTITIAL_IMPRESSION
                ), context
            )

            adView.setAdSize(AdSize.BANNER)
            adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
            admob_banner.addView(adView)
            val adRequest = AdRequest.Builder().build()
            adView.adListener = object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)

                    Log.d("BANNER", "onAdFailedToLoad:$loadAdError")
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    admob_banner.visibility = View.VISIBLE
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
                        ), context
                    )

                }

            }
            adView.loadAd(adRequest)
        }


    }
}