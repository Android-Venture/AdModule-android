package com.sofit.adsimplementationhelper.models

data class AdRequestParamModel(
    var bannerId: String? = null,
    var nativeId: String? = null,
    var interstitialId: String? = null,
    var appOpenAdId: String? = null,
    var interstitialCapping: Int? = null,
    var bannerAdStatus: Boolean? = null,
    var nativeAdStatus: Boolean? = null,
    var interstitialAdStatus: Boolean? = null,
    var appOpenAdStatus: Boolean? = null
)