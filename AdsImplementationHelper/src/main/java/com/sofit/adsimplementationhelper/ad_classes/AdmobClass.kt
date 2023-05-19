package com.sofit.adsimplementationhelper.ad_classes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
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
import com.google.android.material.button.MaterialButton
import com.sofit.adsimplementationhelper.R
import com.sofit.adsimplementationhelper.common.*
import com.sofit.adsimplementationhelper.common.AdInstance.admobNative
import com.sofit.adsimplementationhelper.common.AdInstance.admobInterstitial
import com.sofit.adsimplementationhelper.common.AdInstance.admobInterstitialSplash
import com.sofit.adsimplementationhelper.common.AdLoadCallback
import com.sofit.adsimplementationhelper.common.AdLoadingDialog.hideDialog
import com.sofit.adsimplementationhelper.common.AdLoadingDialog.showDialog
import com.sofit.adsimplementationhelper.models.AdLogModel
import com.sofit.adsimplementationhelper.models.AdRequestParamModel
import kotlinx.coroutines.*

object AdmobClass {

    // NATIVE AD REQUEST

    fun loadNativeAdmob(activity: Activity, requestParams: AdRequestParamModel, className:String) {

        if (admobNative != null) {
            admobNative = null
        }

        if (Utils.isInternetConnected(activity)){

            if (requestParams.nativeAdStatus == true){

                Utils.adLogs.add(className+ NATIVE+ LOADHERE)
                AdLogPrefs.saveLogs(
                    AdLogModel(
                        Utils.adLogs
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

    @SuppressLint("CutPasteId")
    fun inflateAdmob(activity: Activity?, admob_native: NativeAd?, frame_admob: FrameLayout, button_color:Int, textColor:Int, adChoiceColor: Int) {
        val inflater = LayoutInflater.from(activity)
        val adView: NativeAdView? =
            inflater.inflate(R.layout.admob_native_layout, null) as NativeAdView?
        frame_admob.removeAllViews()
        frame_admob.addView(adView)
        adView!!.mediaView = adView.findViewById(R.id.ad_media) as MediaView
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
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

        val button = adView.findViewById<MaterialButton>(R.id.ad_call_to_action)
        val heading_txt = adView.findViewById<TextView>(R.id.ad_headline)
        val body_txt = adView.findViewById<TextView>(R.id.ad_body)
        val ad_choice_txt = adView.findViewById<TextView>(R.id.ad_choice_txt)
        heading_txt.setTextColor(textColor)
        body_txt.setTextColor(textColor)
        ad_choice_txt.setBackgroundResource(adChoiceColor)
        button.backgroundTintList = ColorStateList.valueOf(activity?.resources!!.getColor(button_color))
    }
    fun showNative(activity: Activity?, container: FrameLayout,className: String,button_color: Int,textColor:Int,adChoiceColor: Int) {
        if (admobNative != null) {
            Utils.adLogs.add(className+ NATIVE+ SHOWHERE)
            AdLogPrefs.saveLogs(
                AdLogModel(
                    Utils.adLogs
                ), activity!!
            )
            inflateAdmob(activity, admobNative, container,button_color,textColor,adChoiceColor)

        }

    }
    //INTERSTITIAL

    fun loadAdmobInterstitial(context: Context?, requestParams: AdRequestParamModel, className:String) {
        if (admobInterstitial != null) {
            admobInterstitial = null
        }
        if (Utils.isInternetConnected(context!!)) {

            if (requestParams.interstitialAdStatus==true){

                Utils.adLogs.add(className+ INTERSTITIAL+LOADHERE)
                AdLogPrefs.saveLogs(
                    AdLogModel(
                        Utils.adLogs
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



    @RequiresApi(Build.VERSION_CODES.M)
    fun showAdMobInter(activity: Activity?, requestParams: AdRequestParamModel, className: String, onADClose: (Boolean) -> Unit) {
        if (Utils.interstitialAdCapping == requestParams.interstitialCapping){
            Utils.interstitialAdCapping = 0
            if (admobInterstitial != null ) {
                showDialog(activity!!)
                admobInterstitial!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                    @RequiresApi(Build.VERSION_CODES.M)
                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        onADClose.invoke(true)
                        loadAdmobInterstitial(activity, requestParams,className)

                        Utils.ShowAppOpen = true
                    }


                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()


                        Utils.ShowAppOpen = false
                    }
                    override fun onAdImpression() {
                        super.onAdImpression()
                        Utils.adLogs.add(className+ INTERSTITIAL+SHOWHERE)
                        AdLogPrefs.saveLogs(
                            AdLogModel(
                                Utils.adLogs
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
            }else{
                onADClose.invoke(true)
                loadAdmobInterstitial(activity,requestParams,className)
            }

        }else{
            Utils.interstitialAdCapping++
            onADClose.invoke(true)
        }

    }



    fun loadAdmobInterstitialSplash(context: Context?, requestParams: AdRequestParamModel, className: String,callback: AdLoadCallback){

        if (admobInterstitialSplash != null) {
            admobInterstitialSplash = null
        }


        if (Utils.isInternetConnected(context!!)) {

            if (requestParams.interstitialAdStatus==true){

                Utils.adLogs.add(className+ INTERSTITIAL+LOADHERE)
                AdLogPrefs.saveLogs(
                    AdLogModel(
                        Utils.adLogs
                    ), context
                )
                val request: AdRequest = AdRequest.Builder().build()
                InterstitialAd.load(context, requestParams.interstitialId!!,
                    request, object : InterstitialAdLoadCallback() {
                        override fun onAdLoaded(@NonNull interstitialAd: InterstitialAd) {
                            super.onAdLoaded(interstitialAd)
                            admobInterstitialSplash = interstitialAd

                            callback.onLoaded()

                        }

                        override fun onAdFailedToLoad(@NonNull loadAdError: LoadAdError) {
                            super.onAdFailedToLoad(loadAdError)

                            callback.onFailed()

                        }
                    })

            }
        }

    }


    fun showAdmobInterSplash(activity: Activity?,className: String, onADClose: (Boolean) -> Unit){

        if (admobInterstitialSplash != null) {
            showDialog(activity!!)
            admobInterstitialSplash!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                @RequiresApi(Build.VERSION_CODES.M)
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    onADClose.invoke(true)
                    Utils.ShowAppOpen = true
                }


                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()


                    Utils.ShowAppOpen = false
                }
                override fun onAdImpression() {
                    super.onAdImpression()
                    Utils.adLogs.add(className+ INTERSTITIAL+SHOWHERE)
                    AdLogPrefs.saveLogs(
                        AdLogModel(
                            Utils.adLogs
                        ), activity
                    )

                }
            }
            CoroutineScope(Dispatchers.Default).launch {
                delay(1500)
                withContext(Dispatchers.Main) {
                    hideDialog()
                    admobInterstitialSplash!!.show(activity)
                }
            }
        }else{

            onADClose.invoke(true)
        }


    }



}