package com.sofit.adsimplementationhelper.models

data class AdLogModel(
    val bannerRequestCount: Int,
    val bannerImpressionCount: Int,
    val nativeRequestCount: Int,
    val nativeImpressionCount: Int,
    val interstitialRequestCount: Int,
    val interstitialImpressionCount: Int,
    val bannerRequests: ArrayList<String>,
    val bannerImpressions: ArrayList<String>,
    val nativeRequests: ArrayList<String>,
    val nativeImpressions: ArrayList<String>,
    val interstitialRequests: ArrayList<String>,
    val interstitialImpressions: ArrayList<String>
)
