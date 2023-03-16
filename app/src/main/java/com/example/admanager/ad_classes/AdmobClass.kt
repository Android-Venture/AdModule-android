package com.example.admanager.ad_classes

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
import com.example.admanager.R
import com.example.admanager.common.AdInstance.admobNative1
import com.example.admanager.common.AdInstance.admobNative2
import com.example.admanager.common.AdInstance.admobNativesp
import com.example.admanager.common.AdInstance.admob_interstitial
import com.example.admanager.common.AdLoadingDialog.hideDialog
import com.example.admanager.common.AdLoadingDialog.showDialog
import com.example.admanager.common.AdLogPrefs
import com.example.admanager.common.Utils
import com.example.admanager.models.AdLogModel
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import kotlinx.coroutines.*

object AdmobClass {

        var sendrequest1 = true
        var sendrequest2 = true
        var tag_splash = false

        // NATIVE AD REQUEST
        fun load_native_admob1(activity: Activity?,adUnitId:String) {

            if (admobNative1 != null) {
                admobNative1 = null
            }

            Utils.NATIVE_REQUEST++
            AdLogPrefs.saveLogs(AdLogModel(Utils.BANNER_REQUEST,Utils.BANNER_IMPRESSION,Utils.NATIVE_REQUEST,Utils.NATIVE_IMPRESSION,Utils.INTERSTITIAL_REQUEST,Utils.INTERSTITIAL_IMPRESSION),activity!!)

            val builder: AdLoader.Builder = AdLoader.Builder(
                activity!!,adUnitId
            )
            builder.forNativeAd(NativeAd.OnNativeAdLoadedListener { nativeAd ->
                if (admobNative1 != null) {
                    admobNative1!!.destroy()
                }
                admobNative1 = nativeAd
            })
            val options = VideoOptions.Builder().setStartMuted(true).build()
            val nativeAdOptions: NativeAdOptions = NativeAdOptions.Builder().setVideoOptions(options).build()
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
                    load_native_admob1(activity,adUnitId)
                    Utils.NATIVE_IMPRESSION++
                    AdLogPrefs.saveLogs(AdLogModel(Utils.BANNER_REQUEST,Utils.BANNER_IMPRESSION,Utils.NATIVE_REQUEST,Utils.NATIVE_IMPRESSION,Utils.INTERSTITIAL_REQUEST,Utils.INTERSTITIAL_IMPRESSION),activity!!)

                }
            }).build()
            loader.loadAd(AdRequest.Builder().build())


        }

        fun load_native_admob2(activity: Activity?,adUnitId:String) {


            if (admobNative2 != null) {
                admobNative2 = null
            }

            Utils.NATIVE_REQUEST++
            AdLogPrefs.saveLogs(AdLogModel(Utils.BANNER_REQUEST,Utils.BANNER_IMPRESSION,Utils.NATIVE_REQUEST,Utils.NATIVE_IMPRESSION,Utils.INTERSTITIAL_REQUEST,Utils.INTERSTITIAL_IMPRESSION),activity!!)

            val builder: AdLoader.Builder = AdLoader.Builder(
                activity!!, adUnitId)
            builder.forNativeAd(NativeAd.OnNativeAdLoadedListener { nativeAd ->
                if (admobNative2 != null) {
                    admobNative2!!.destroy()
                }
                admobNative2 = nativeAd
            })
            val options = VideoOptions.Builder().setStartMuted(true).build()
            val nativeAdOptions: NativeAdOptions = NativeAdOptions.Builder().setVideoOptions(options).build()
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
                    load_native_admob2(activity,adUnitId)
                    Utils.NATIVE_IMPRESSION++
                    AdLogPrefs.saveLogs(AdLogModel(Utils.BANNER_REQUEST,Utils.BANNER_IMPRESSION,Utils.NATIVE_REQUEST,Utils.NATIVE_IMPRESSION,Utils.INTERSTITIAL_REQUEST,Utils.INTERSTITIAL_IMPRESSION),activity!!)

                }
            }).build()
            loader.loadAd(AdRequest.Builder().build())


        }

        fun load_native_admobsp(activity: Activity?, container: FrameLayout) {

            if (admobNativesp != null) {
                admobNativesp = null
            }


            val builder: AdLoader.Builder = AdLoader.Builder(
                activity!!,""
            )
            builder.forNativeAd(NativeAd.OnNativeAdLoadedListener { nativeAd ->
                if (admobNativesp != null) {
                    admobNativesp!!.destroy()
                }
                admobNativesp = nativeAd
            })
            val options = VideoOptions.Builder().setStartMuted(true).build()
            val nativeAdOptions: NativeAdOptions = NativeAdOptions.Builder().setVideoOptions(options).build()
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

        fun showNative(activity: Activity?, container: FrameLayout,adUnitId: String) {

            if (admobNative1 != null) {
                inflateAdmob(activity, admobNative1, container)

            } else if (admobNative2 != null) {
                inflateAdmob(activity, admobNative2, container)

            } else {
                if (!sendrequest1) {
                    load_native_admob1(activity,adUnitId)

                }
                if (!sendrequest2) {
                    load_native_admob2(activity,adUnitId)
                }
            }

        }



        //INTERSTITIAL
        fun loadadmob_Interstitial(context: Context?, adUnitId:String) {
            if (admob_interstitial != null) {
                admob_interstitial = null
            }
            Utils.INTERSTITIAL_REQUEST++
            AdLogPrefs.saveLogs(AdLogModel(Utils.BANNER_REQUEST,Utils.BANNER_IMPRESSION,Utils.NATIVE_REQUEST,Utils.NATIVE_IMPRESSION,Utils.INTERSTITIAL_REQUEST,Utils.INTERSTITIAL_IMPRESSION),context!!)
            val request: AdRequest =    AdRequest.Builder().build()
            InterstitialAd.load(context!!,adUnitId,
                request, object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(@NonNull interstitialAd: InterstitialAd) {
                        super.onAdLoaded(interstitialAd)
                        admob_interstitial = interstitialAd
                        Log.d("loadChck", "Loaded")
                    }

                    override fun onAdFailedToLoad(@NonNull loadAdError: LoadAdError) {
                        super.onAdFailedToLoad(loadAdError)
                        Log.d("loadChck", "Error")
                    }
                })

        }


    fun showAdMobInter(activity: Activity?,adUnitId: String, onADClose: (Boolean) -> Unit) {
        if (admob_interstitial != null) {
            showDialog(activity!!)
            admob_interstitial!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    onADClose.invoke(true)
                    loadadmob_Interstitial(activity,adUnitId)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                      Utils.INTERSTITIAL_IMPRESSION++
                    AdLogPrefs.saveLogs(AdLogModel(Utils.BANNER_REQUEST,Utils.BANNER_IMPRESSION,Utils.NATIVE_REQUEST,Utils.NATIVE_IMPRESSION,Utils.INTERSTITIAL_REQUEST,Utils.INTERSTITIAL_IMPRESSION),activity!!)

                }
            }

            CoroutineScope(Dispatchers.Default).launch {
                delay(1500)
                withContext(Dispatchers.Main) {
                    hideDialog()
                    admob_interstitial!!.show(activity)
                }
            }
        }
    }

}