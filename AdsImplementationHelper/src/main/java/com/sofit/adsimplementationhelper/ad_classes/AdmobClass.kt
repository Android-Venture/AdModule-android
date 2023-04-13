package com.sofit.adsimplementationhelper.ad_classes

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import com.example.admanager.models.AdLogModel
import com.example.admanager.models.AdRequestParamModel
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.sofit.adsimplementationhelper.R
import com.sofit.adsimplementationhelper.common.AdInstance.admobNative1
import com.sofit.adsimplementationhelper.common.AdInstance.admobNative2
import com.sofit.adsimplementationhelper.common.AdInstance.admobNativesp
import com.sofit.adsimplementationhelper.common.AdInstance.admob_interstitial1
import com.sofit.adsimplementationhelper.common.AdInstance.admob_interstitial2
import com.sofit.adsimplementationhelper.common.AdLoadingDialog.hideDialog
import com.sofit.adsimplementationhelper.common.AdLoadingDialog.showDialog
import com.sofit.adsimplementationhelper.common.AdLogPrefs
import com.sofit.adsimplementationhelper.common.Utils
import kotlinx.coroutines.*

object AdmobClass {


    // NATIVE AD REQUEST
    fun load_native_admob1(activity: Activity?, requestParams:AdRequestParamModel) {

        if (admobNative1 != null) {
            admobNative1 = null
        }

        if (Utils.isInternetConnected(activity!!) && requestParams.native_ad_status!!){
            Utils.NATIVE_REQUEST++
            AdLogPrefs.saveLogs(
                AdLogModel(
                    Utils.BANNER_REQUEST,
                    Utils.BANNER_IMPRESSION,
                    Utils.NATIVE_REQUEST,
                    Utils.NATIVE_IMPRESSION,
                    Utils.INTERSTITIAL_REQUEST,
                    Utils.INTERSTITIAL_IMPRESSION
                ), activity!!
            )
            val builder: AdLoader.Builder = AdLoader.Builder(
                activity!!, requestParams.native_id!!
            )
            builder.forNativeAd(NativeAd.OnNativeAdLoadedListener { nativeAd ->
                if (admobNative1 != null) {
                    admobNative1!!.destroy()
                }
                admobNative1 = nativeAd
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
                    Log.d("ADMOB_NATIVE", "onAdClosed: ")
                }
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.d("ADMOB_NATIVE", "onAdLoaded:")
                }
                override fun onAdImpression() {
                    super.onAdImpression()
                    load_native_admob1(activity, requestParams)
                    Utils.NATIVE_IMPRESSION++
                    AdLogPrefs.saveLogs(
                        AdLogModel(
                            Utils.BANNER_REQUEST,
                            Utils.BANNER_IMPRESSION,
                            Utils.NATIVE_REQUEST,
                            Utils.NATIVE_IMPRESSION,
                            Utils.INTERSTITIAL_REQUEST,
                            Utils.INTERSTITIAL_IMPRESSION
                        ), activity!!
                    )
                }
            }).build()
            loader.loadAd(AdRequest.Builder().build())
        }


    }

    fun load_native_admob2(activity: Activity?, requestParams:AdRequestParamModel) {

        if (admobNative2 != null) {
            admobNative2 = null
        }
        if (Utils.isInternetConnected(activity!!) && requestParams.native_ad_status!!) {
            Utils.NATIVE_REQUEST++
            AdLogPrefs.saveLogs(
                AdLogModel(
                    Utils.BANNER_REQUEST,
                    Utils.BANNER_IMPRESSION,
                    Utils.NATIVE_REQUEST,
                    Utils.NATIVE_IMPRESSION,
                    Utils.INTERSTITIAL_REQUEST,
                    Utils.INTERSTITIAL_IMPRESSION
                ), activity!!
            )
            val builder: AdLoader.Builder = AdLoader.Builder(
                activity!!,requestParams.native_id!!
            )
            builder.forNativeAd(NativeAd.OnNativeAdLoadedListener { nativeAd ->
                if (admobNative2 != null) {
                    admobNative2!!.destroy()
                }
                admobNative2 = nativeAd
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
                    Log.d("ADMOB_NATIVE", "onAdClosed: ")
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.d("ADMOB_NATIVE", "onAdLoaded:")
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    load_native_admob2(activity, requestParams)
                    Utils.NATIVE_IMPRESSION++
                    AdLogPrefs.saveLogs(
                        AdLogModel(
                            Utils.BANNER_REQUEST,
                            Utils.BANNER_IMPRESSION,
                            Utils.NATIVE_REQUEST,
                            Utils.NATIVE_IMPRESSION,
                            Utils.INTERSTITIAL_REQUEST,
                            Utils.INTERSTITIAL_IMPRESSION
                        ), activity!!
                    )

                }
            }).build()
            loader.loadAd(AdRequest.Builder().build())
        }
    }

    fun load_native_admobsp(activity: Activity?, container: FrameLayout) {
        if (admobNativesp != null) {
            admobNativesp = null
        }
        val builder: AdLoader.Builder = AdLoader.Builder(
            activity!!, ""
        )
        builder.forNativeAd(NativeAd.OnNativeAdLoadedListener { nativeAd ->
            if (admobNativesp != null) {
                admobNativesp!!.destroy()
            }
            admobNativesp = nativeAd
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
                Log.d("ADMOB_NATIVE", "onAdClosed: ")
            }
            override fun onAdLoaded() {
                super.onAdLoaded()

            }
            override fun onAdImpression() {
                super.onAdImpression()
            }
        }).build()
        loader.loadAd(AdRequest.Builder().build())

    }

    fun inflateAdmob(activity: Activity?, admob_native: NativeAd?, frame_admob: FrameLayout) {
        val inflater = LayoutInflater.from(activity)
        val adView: NativeAdView? =
            inflater.inflate(R.layout.admob_native_hctr, null) as NativeAdView?
        frame_admob.removeAllViews()
        frame_admob.addView(adView)
        adView!!.setMediaView(adView.findViewById(R.id.ad_media_admob_hctr) as MediaView)
        adView.setIconView(adView.findViewById(R.id.ad_app_icon_admob_hctr))
        adView.setBodyView(adView.findViewById(R.id.ad_body_admob_hctr))
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline_admob_hctr))
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action_admob_hctr))
        if (admob_native!!.body == null) {
            if (adView != null) {
                adView.getBodyView()!!.setVisibility(View.INVISIBLE)
            }
        } else {
            if (adView != null) {
                (adView.getBodyView() as TextView).text = admob_native.body
                (adView.getBodyView() as TextView).setVisibility(View.VISIBLE)
            }
        }
        if (admob_native.headline == null) {
            adView.getHeadlineView()!!.setVisibility(View.INVISIBLE)
        } else {
            (adView.getHeadlineView() as TextView).text = admob_native.headline
            (adView.getHeadlineView() as TextView).setVisibility(View.VISIBLE)
        }
        if (admob_native.callToAction == null) {
            adView.getCallToActionView()!!.setVisibility(View.INVISIBLE)
        } else {
            (adView.getCallToActionView() as Button).setText(admob_native.callToAction)
            (adView.getCallToActionView() as Button).setVisibility(View.VISIBLE)
        }
        if (admob_native.icon == null) {
            adView.getIconView()!!.setVisibility(View.INVISIBLE)
        } else {
            (adView.getIconView() as ImageView).setImageDrawable(admob_native.icon!!.drawable)
            (adView.getIconView() as ImageView).setVisibility(View.VISIBLE)
        }
        adView.setNativeAd(admob_native)
    }

    fun showNative(activity: Activity?, container: FrameLayout, requestParams: AdRequestParamModel) {

        if (admobNative1 != null) {
            inflateAdmob(activity, admobNative1, container)

        }


    }

    //INTERSTITIAL
    fun loadadmob_Interstitial(context: Context?, requestParams: AdRequestParamModel) {
        if (admob_interstitial1 != null) {
            admob_interstitial1 = null
        }

        if (Utils.isInternetConnected(context!!) && requestParams.intersitial_ad_status!!) {
            Utils.INTERSTITIAL_REQUEST++
            AdLogPrefs.saveLogs(
                AdLogModel(
                    Utils.BANNER_REQUEST,
                    Utils.BANNER_IMPRESSION,
                    Utils.NATIVE_REQUEST,
                    Utils.NATIVE_IMPRESSION,
                    Utils.INTERSTITIAL_REQUEST,
                    Utils.INTERSTITIAL_IMPRESSION
                ), context!!
            )
            val request: AdRequest = AdRequest.Builder().build()
            InterstitialAd.load(context!!, requestParams.intersitial_id!!,
                request, object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(@NonNull interstitialAd: InterstitialAd) {
                        super.onAdLoaded(interstitialAd)
                        admob_interstitial1 = interstitialAd
                        Log.d("loadChck", "Loaded")
                    }

                    override fun onAdFailedToLoad(@NonNull loadAdError: LoadAdError) {
                        super.onAdFailedToLoad(loadAdError)
                        Log.d("loadChck", "Error")
                    }
                })

        }
    }

    fun showAdMobInter(activity: Activity?, requestParams: AdRequestParamModel, onADClose: (Boolean) -> Unit) {
        if (admob_interstitial1 != null) {
            showDialog(activity!!)
            admob_interstitial1!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    onADClose.invoke(true)
                    loadadmob_Interstitial(activity, requestParams)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Utils.INTERSTITIAL_IMPRESSION++
                    AdLogPrefs.saveLogs(
                        AdLogModel(
                            Utils.BANNER_REQUEST,
                            Utils.BANNER_IMPRESSION,
                            Utils.NATIVE_REQUEST,
                            Utils.NATIVE_IMPRESSION,
                            Utils.INTERSTITIAL_REQUEST,
                            Utils.INTERSTITIAL_IMPRESSION
                        ), activity!!
                    )

                }
            }
            CoroutineScope(Dispatchers.Default).launch {
                delay(1500)
                withContext(Dispatchers.Main) {
                    hideDialog()
                    admob_interstitial1!!.show(activity)
                }
            }
        }
    }


    //INTERSTITIAL
    fun loadadmob_Interstitial2(context: Context?, requestParams: AdRequestParamModel) {
        if (admob_interstitial2 != null) {
            admob_interstitial2= null
        }

        if (Utils.isInternetConnected(context!!) && requestParams.intersitial_ad_status!!) {
            Utils.INTERSTITIAL_REQUEST++
            AdLogPrefs.saveLogs(
                AdLogModel(
                    Utils.BANNER_REQUEST,
                    Utils.BANNER_IMPRESSION,
                    Utils.NATIVE_REQUEST,
                    Utils.NATIVE_IMPRESSION,
                    Utils.INTERSTITIAL_REQUEST,
                    Utils.INTERSTITIAL_IMPRESSION
                ), context!!
            )
            val request: AdRequest = AdRequest.Builder().build()
            InterstitialAd.load(context!!, requestParams.intersitial_id!!,
                request, object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(@NonNull interstitialAd: InterstitialAd) {
                        super.onAdLoaded(interstitialAd)
                        admob_interstitial2 = interstitialAd
                        Log.d("loadChck", "Loaded")
                    }

                    override fun onAdFailedToLoad(@NonNull loadAdError: LoadAdError) {
                        super.onAdFailedToLoad(loadAdError)
                        Log.d("loadChck", "Error")
                    }
                })

        }
    }

    fun showAdMobInter2(activity: Activity?, requestParams: AdRequestParamModel, onADClose: (Boolean) -> Unit) {
        if (admob_interstitial2 != null) {
            showDialog(activity!!)
            admob_interstitial2!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    onADClose.invoke(true)
                    loadadmob_Interstitial(activity, requestParams)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Utils.INTERSTITIAL_IMPRESSION++
                    AdLogPrefs.saveLogs(
                        AdLogModel(
                            Utils.BANNER_REQUEST,
                            Utils.BANNER_IMPRESSION,
                            Utils.NATIVE_REQUEST,
                            Utils.NATIVE_IMPRESSION,
                            Utils.INTERSTITIAL_REQUEST,
                            Utils.INTERSTITIAL_IMPRESSION
                        ), activity!!
                    )

                }
            }
            CoroutineScope(Dispatchers.Default).launch {
                delay(1500)
                withContext(Dispatchers.Main) {
                    hideDialog()
                    admob_interstitial2!!.show(activity)
                }
            }
        }
    }

}