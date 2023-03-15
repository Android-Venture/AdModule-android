package com.example.admanager.ad_classes

import android.app.Activity
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder
import com.example.admanager.R

class AppLovinClass {

    companion object{
        var applovinNative1: MaxAd? = null
        var nativeAdView1: MaxNativeAdView? = null

        var applovinNative2: MaxAd? = null
        var nativeAdView2: MaxNativeAdView? = null

        var applovinNativesplash: MaxAd? = null
        var nativeAdViewsplash: MaxNativeAdView? = null

        var interstitialAd_applovin: MaxInterstitialAd? = null
        var interstitialAd_applovin2: MaxInterstitialAd? = null
        var applovin1 = false
        var applovin2 = false


        fun loadApplovinInter1(activity: Activity?) {

            if (interstitialAd_applovin != null && interstitialAd_applovin!!.isReady) {
                interstitialAd_applovin = null
            }
            interstitialAd_applovin = MaxInterstitialAd("bc682675ef6fe4d3", activity)
            interstitialAd_applovin!!.loadAd()

        }

        fun loadApplovinInter2(activity: Activity?) {

            if (interstitialAd_applovin2 != null && interstitialAd_applovin!!.isReady) {
                interstitialAd_applovin2 = null
            }
            interstitialAd_applovin2 = MaxInterstitialAd("bc682675ef6fe4d3", activity)
            interstitialAd_applovin2!!.loadAd()

        }


        fun showInterApplovin(listener: MaxAdListener?, activity: Activity?) {
            if (interstitialAd_applovin != null && interstitialAd_applovin!!.isReady) {
                interstitialAd_applovin!!.setListener(listener)
                interstitialAd_applovin!!.showAd()
            } else {
                loadApplovinInter1(activity)
                interstitialAd_applovin2!!.setListener(listener)
                interstitialAd_applovin2!!.showAd()
            }
        }


        fun loadApplovin1(activity: Activity?) {

            val adLoader = MaxNativeAdLoader("", activity)
            adLoader.setNativeAdListener(object : MaxNativeAdListener() {
                override fun onNativeAdLoaded(maxNativeAdView: MaxNativeAdView?, maxAd: MaxAd) {
                    super.onNativeAdLoaded(maxNativeAdView, maxAd)
                    applovin1 = true
                    applovinNative1 = maxAd
                    nativeAdView1 = maxNativeAdView
                }

                override fun onNativeAdLoadFailed(s: String, maxError: MaxError) {
                    super.onNativeAdLoadFailed(s, maxError)
                    applovin1 = false
                    Log.d("APP_LOVIN", "onNativeAdLoadFailed: " + maxError.code)
                }

                override fun onNativeAdClicked(maxAd: MaxAd) {
                    super.onNativeAdClicked(maxAd)
                }
            })

            nativeAdView1 = MaxNativeAdView(bindApplovingLayout(), activity)
            adLoader.loadAd(nativeAdView1)


        }

        fun loadApplovin2(activity: Activity?) {

            val adLoader = MaxNativeAdLoader("", activity)
            adLoader.setNativeAdListener(object : MaxNativeAdListener() {
                override fun onNativeAdLoaded(maxNativeAdView: MaxNativeAdView?, maxAd: MaxAd) {
                    super.onNativeAdLoaded(maxNativeAdView, maxAd)
                    applovin2 = true
                    Log.d("APP_LOVIN", "onNativeAdLoad")
                    applovinNative2 = maxAd
                    nativeAdView2 = maxNativeAdView
                }

                override fun onNativeAdLoadFailed(s: String, maxError: MaxError) {
                    super.onNativeAdLoadFailed(s, maxError)
                    applovin2 = false
                    Log.d("APP_LOVIN", "onNativeAdLoadFailed: " + maxError.code)
                }

                override fun onNativeAdClicked(maxAd: MaxAd) {
                    super.onNativeAdClicked(maxAd)
                }
            })


            nativeAdView2 = MaxNativeAdView(bindApplovingLayout(), activity)
            adLoader.loadAd(nativeAdView2)


        }

        fun loadApplovinsp(activity: Activity?, container: FrameLayout) {
            val adLoader = MaxNativeAdLoader("", activity)
            adLoader.setNativeAdListener(object : MaxNativeAdListener() {
                override fun onNativeAdLoaded(maxNativeAdView: MaxNativeAdView?, maxAd: MaxAd) {
                    super.onNativeAdLoaded(maxNativeAdView, maxAd)
                    Log.d("APP_LOVIN", "onNativeAdLoad")
                    applovinNativesplash = maxAd
                    nativeAdViewsplash = maxNativeAdView
                    inflateNativesp(container, activity)
                }

                override fun onNativeAdLoadFailed(s: String, maxError: MaxError) {
                    super.onNativeAdLoadFailed(s, maxError)
                    Log.d("APP_LOVIN", "onNativeAdLoadFailed: " + maxError.code)
                }

                override fun onNativeAdClicked(maxAd: MaxAd) {
                    super.onNativeAdClicked(maxAd)
                }
            })

            nativeAdViewsplash = MaxNativeAdView(bindApplovingLayout(), activity)
            adLoader.loadAd(nativeAdViewsplash)


        }

        fun inflateNative1(container: FrameLayout, activity: Activity?) {
            if (applovinNative1 != null && nativeAdView1 != null) {
                if (nativeAdView1!!.parent != null) {
                    (nativeAdView1!!.parent as ViewGroup).removeView(nativeAdView1)
                }
                //            container.removeAllViews();
                container.addView(nativeAdView1)
                loadApplovin1(activity)
            }
        }

        fun inflateNative2(container: FrameLayout, activity: Activity?) {
            if (applovinNative2 != null && nativeAdView2 != null) {
                if (nativeAdView2!!.parent != null) {
                    (nativeAdView2!!.parent as ViewGroup).removeView(nativeAdView2) // <- fix
                }
                //            container.removeAllViews();
                container.addView(nativeAdView2)

                loadApplovin2(activity)
            }
        }

        fun inflateNativesp(container: FrameLayout, activity: Activity?) {
            if (applovinNativesplash != null && nativeAdViewsplash != null) {
                if (nativeAdViewsplash!!.parent != null) {
                    (nativeAdViewsplash!!.parent as ViewGroup).removeView(nativeAdViewsplash) // <- fix
                }
                container.addView(nativeAdViewsplash)

                loadApplovin2(activity)
            }
        }

        fun showApplovin(container: FrameLayout, activity: Activity?) {

            if (applovinNative1 != null) {
                inflateNative1(container, activity)
            } else if (applovinNative2 != null) {
                inflateNative2(container, activity)
                loadApplovin1(activity)
            } else {
                if (!applovin1) {
                    loadApplovin1(activity)
                } else if (!applovin2) {
                    loadApplovin2(activity)
                }
            }
        }
        fun bindApplovingLayout(): MaxNativeAdViewBinder? {
            val layout: Int
            layout = R.layout.applovin_top
//                if (PowerPreference.getDefaultFile().getString("FB_CTA_INNER") == "bcta") {
//                R.layout.applovin_bottom
//            } else {

//            }
            return MaxNativeAdViewBinder.Builder(layout)
                .setTitleTextViewId(R.id.title_text_view)
                .setBodyTextViewId(R.id.body_text_view)
                .setIconImageViewId(R.id.icon_image_view)
                .setMediaContentViewGroupId(R.id.media_view_container)
                .setOptionsContentViewGroupId(com.applovin.sdk.R.id.options_view)
                .setCallToActionButtonId(R.id.cta_button)
                .setOptionsContentViewGroupId(R.id.ad_options_view)
                .build()
        }
    }
}