package com.sofit.adsimplementationhelper.ad_classes

import android.app.Activity
import android.os.Build
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.google.android.gms.ads.*
import com.sofit.adsimplementationhelper.common.AdLogPrefs
import com.sofit.adsimplementationhelper.common.LOADHERE
import com.sofit.adsimplementationhelper.common.SHOWHERE
import com.sofit.adsimplementationhelper.common.Utils
import com.sofit.adsimplementationhelper.common.Utils.isInternetConnected
import com.sofit.adsimplementationhelper.models.AdLogModel
import com.sofit.adsimplementationhelper.models.AdRequestParamModel

object AdmobBanner {
    @RequiresApi(Build.VERSION_CODES.R)
    fun showAdmobBanner(
        admob_banner: FrameLayout,
        activity: Activity,
        requestParams: AdRequestParamModel,
        callback: com.sofit.adsimplementationhelper.common.AdLoadCallback,
        class_name: String
    ) {
        if (isInternetConnected(activity)) {
            if (requestParams.bannerAdStatus == true){
                val adView = AdView(activity)
                adView.setAdSize(Utils.getAdSize(activity,admob_banner))
                adView.adUnitId = requestParams.bannerId!!
                admob_banner.addView(adView)
                val adRequest = AdRequest.Builder().build()
                adView.adListener = object : AdListener() {
                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        super.onAdFailedToLoad(loadAdError)
                        callback.onFailed()

                    }
                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        callback.onLoaded()
                        Utils.bannerRequests.add(class_name+ LOADHERE)
                        Utils.BannerRequest++
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
                    override fun onAdImpression() {
                        super.onAdImpression()
                        Utils.BannerImpression++
                        Utils.bannerImpressions.add(class_name+ SHOWHERE)
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
                adView.loadAd(adRequest)
            }
        }
    }
}