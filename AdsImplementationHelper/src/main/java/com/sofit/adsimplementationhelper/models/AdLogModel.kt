package com.example.admanager.models

data class AdLogModel(
    //TODO: Avoid using snake case instead use Camel case for kotlin and snake case for xml
    val banner_request: Int,
    val banner_impression: Int,
    val native_request: Int,
    val native_impression: Int,
    val interstitial_request: Int,
    val interstitial_impression: Int
)
