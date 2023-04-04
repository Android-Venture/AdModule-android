package com.sofit.adsimplementationhelper.common

import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd

object AdInstance {
    //TODO: Avoid using snake case and camel case both, instead use Camel case for kotlin and snake case for xml

    var admobNative1: NativeAd? = null
    var admobNative2: NativeAd? = null
    var admobNativesp: NativeAd? = null

    var admob_interstitial1: InterstitialAd? = null
    var admob_interstitial2:InterstitialAd? = null
}