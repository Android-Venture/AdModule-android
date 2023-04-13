package com.example.admanager.models

data class AdLogModel(
    val banner_request: Int,
    val banner_impression: Int,
    val native_request: Int,
    val native_impression: Int,
    val interstitial_request: Int,
    val interstitial_impression: Int,
    val bannerRequest: ArrayList<String>,
    val bannerImpression: ArrayList<String>,
    val nativeRequest: ArrayList<String>,
    val nativeImpression: ArrayList<String>,
    val interstitialRequest: ArrayList<String>,
    val interstitialImpression:ArrayList<String>
)
