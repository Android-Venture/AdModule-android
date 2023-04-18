package com.sofit.adsimplementationhelper.ad_classes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.sofit.adsimplementationhelper.R
import com.sofit.adsimplementationhelper.common.AdInstance.admobNative
import com.sofit.adsimplementationhelper.common.AdInstance.admobInterstitial
import com.sofit.adsimplementationhelper.common.AdLoadingDialog.hideDialog
import com.sofit.adsimplementationhelper.common.AdLoadingDialog.showDialog
import com.sofit.adsimplementationhelper.common.AdLogPrefs
import com.sofit.adsimplementationhelper.common.LOADHERE
import com.sofit.adsimplementationhelper.common.SHOWHERE
import com.sofit.adsimplementationhelper.common.Utils
import com.sofit.adsimplementationhelper.models.AdLogModel
import com.sofit.adsimplementationhelper.models.AdRequestParamModel
import kotlinx.coroutines.*

object AdmobClass {

    // NATIVE AD REQUEST
    @RequiresApi(Build.VERSION_CODES.M)
    fun loadNativeAdmob(activity: Activity, requestParams: AdRequestParamModel, className:String) {

        if (admobNative != null) {
            admobNative = null
        }

        if (Utils.isInternetConnected(activity)){

            if (requestParams.nativeAdStatus == true){
                Utils.NativeRequest++
                Utils.nativeRequests.add(className+ LOADHERE)
                AdLogPrefs.saveLogs(
                    AdLogModel(
                        Utils.BannerRequest,
                        Utils.BannerImpression,
                        Utils.NativeRequest,
                        Utils.NativeImpression,
                        Utils.InterstitialRequest,
                        Utils.InterstitialImpression,
                        Utils.bannerRequests,
                        Utils.bannerImpressions,
                        Utils.nativeRequests,
                        Utils.nativeImpressions,
                        Utils.interstistialRequests,
                        Utils.interstitialImpressions
                    ), activity
                )
                val builder: AdLoader.Builder = AdLoader.Builder(
                    activity, requestParams.nativeId!!
                )
                builder.forNativeAd(NativeAd.OnNativeAdLoadedListener { nativeAd ->
                    if (admobNative != null) {
                        admobNative!!.destroy()
                    }
                    admobNative = nativeAd
                })
                val options = VideoOptions.Builder().setStartMuted(true).build()
                val nativeAdOptions: NativeAdOptions =
                    NativeAdOptions.Builder().setVideoOptions(options).build()
                builder.withNativeAdOptions(nativeAdOptions)
                val loader: AdLoader = builder.withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        super.onAdFailedToLoad(loadAdError)

                    }
                    override fun onAdClosed() {
                        super.onAdClosed()

                    }
                    override fun onAdLoaded() {
                        super.onAdLoaded()
                    }
                    override fun onAdImpression() {
                        super.onAdImpression()
                        loadNativeAdmob(activity, requestParams,className)

                    }
                }).build()
                loader.loadAd(AdRequest.Builder().build())
            }

        }


    }
    @SuppressLint("InflateParams")
    fun inflateAdmob(activity: Activity?, admob_native: NativeAd?, frame_admob: FrameLayout) {
        val inflater = LayoutInflater.from(activity)
        val adView: NativeAdView? =
            inflater.inflate(R.layout.admob_native_hctr, null) as NativeAdView?
        frame_admob.removeAllViews()
        frame_admob.addView(adView)
        adView!!.mediaView = adView.findViewById(R.id.ad_media_admob_hctr) as MediaView
        adView.iconView = adView.findViewById(R.id.ad_app_icon_admob_hctr)
        adView.bodyView = adView.findViewById(R.id.ad_body_admob_hctr)
        adView.headlineView = adView.findViewById(R.id.ad_headline_admob_hctr)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action_admob_hctr)
        if (admob_native!!.body == null) {
            if (adView != null) {
                adView.bodyView!!.visibility = View.INVISIBLE
            }
        } else {
            if (adView != null) {
                (adView.bodyView as TextView).text = admob_native.body
                (adView.bodyView as TextView).visibility = View.VISIBLE
            }
        }
        if (admob_native.headline == null) {
            adView.headlineView!!.visibility = View.INVISIBLE
        } else {
            (adView.headlineView as TextView).text = admob_native.headline
            (adView.headlineView as TextView).visibility = View.VISIBLE
        }
        if (admob_native.callToAction == null) {
            adView.callToActionView!!.visibility = View.INVISIBLE
        } else {
            (adView.callToActionView as Button).text = admob_native.callToAction
            (adView.callToActionView as Button).visibility = View.VISIBLE
        }
        if (admob_native.icon == null) {
            adView.iconView!!.visibility = View.INVISIBLE
        } else {
            (adView.iconView as ImageView).setImageDrawable(admob_native.icon!!.drawable)
            (adView.iconView as ImageView).visibility = View.VISIBLE
        }
        adView.setNativeAd(admob_native)
    }
    fun showNative(activity: Activity?, container: FrameLayout, requestParams: AdRequestParamModel,className: String) {
        if (admobNative != null) {
            Utils.NativeImpression++
            Utils.nativeImpressions.add(className+ SHOWHERE)
            AdLogPrefs.saveLogs(
                AdLogModel(
                    Utils.BannerRequest,
                    Utils.BannerImpression,
                    Utils.NativeRequest,
                    Utils.NativeImpression,
                    Utils.InterstitialRequest,
                    Utils.InterstitialImpression,
                    Utils.bannerRequests,
                    Utils.bannerImpressions,
                    Utils.nativeRequests,
                    Utils.nativeImpressions,
                    Utils.interstistialRequests,
                    Utils.interstitialImpressions
                ), activity!!
            )
            inflateAdmob(activity, admobNative, container)

        }

    }
    //INTERSTITIAL
    @RequiresApi(Build.VERSION_CODES.M)
    fun loadAdmobInterstitial(context: Context?, requestParams: AdRequestParamModel, className:String) {
        if (admobInterstitial != null) {
            admobInterstitial = null
        }
        if (Utils.isInternetConnected(context!!)) {

            if (requestParams.interstitialAdStatus==true){

                Utils.InterstitialRequest++
                Utils.interstistialRequests.add(className+ LOADHERE)
                AdLogPrefs.saveLogs(
                    AdLogModel(
                        Utils.BannerRequest,
                        Utils.BannerImpression,
                        Utils.NativeRequest,
                        Utils.NativeImpression,
                        Utils.InterstitialRequest,
                        Utils.InterstitialImpression,
                        Utils.bannerRequests,
                        Utils.bannerImpressions,
                        Utils.nativeRequests,
                        Utils.nativeImpressions,
                        Utils.interstistialRequests,
                        Utils.interstitialImpressions
                    ), context
                )
                val request: AdRequest = AdRequest.Builder().build()
                InterstitialAd.load(context, requestParams.interstitialId!!,
                    request, object : InterstitialAdLoadCallback() {
                        override fun onAdLoaded(@NonNull interstitialAd: InterstitialAd) {
                            super.onAdLoaded(interstitialAd)
                            admobInterstitial = interstitialAd

                        }

                        override fun onAdFailedToLoad(@NonNull loadAdError: LoadAdError) {
                            super.onAdFailedToLoad(loadAdError)

                        }
                    })

            }
        }
    }

    fun showAdMobInter(activity: Activity?, requestParams: AdRequestParamModel,className: String, onADClose: (Boolean) -> Unit) {
        if (admobInterstitial != null) {
            showDialog(activity!!)
            admobInterstitial!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                @RequiresApi(Build.VERSION_CODES.M)
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    onADClose.invoke(true)
                    loadAdmobInterstitial(activity, requestParams,className)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Utils.InterstitialImpression++
                    Utils.interstitialImpressions.add(className+ SHOWHERE)
                    AdLogPrefs.saveLogs(
                        AdLogModel(
                            Utils.BannerRequest,
                            Utils.BannerImpression,
                            Utils.NativeRequest,
                            Utils.NativeImpression,
                            Utils.InterstitialRequest,
                            Utils.InterstitialImpression,
                            Utils.bannerRequests,
                            Utils.bannerImpressions,
                            Utils.nativeRequests,
                            Utils.nativeImpressions,
                            Utils.interstistialRequests,
                            Utils.interstitialImpressions
                        ), activity
                    )

                }
            }
            CoroutineScope(Dispatchers.Default).launch {
                delay(1500)
                withContext(Dispatchers.Main) {
                    hideDialog()
                    admobInterstitial!!.show(activity)
                }
            }
        }
    }



}