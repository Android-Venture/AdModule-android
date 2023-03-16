package com.example.admanager

import android.app.Application
import com.example.admanager.ad_classes.AdmobClass
import com.example.admanager.ad_classes.AppOpenManager
import com.google.android.gms.ads.MobileAds

class ApplicationClass:Application() {
    var appOpenManager: AppOpenManager? = null

    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this){

            AdmobClass.loadadmob_Interstitial(this,  "ca-app-pub-3940256099942544/8691691433")

            appOpenManager = AppOpenManager(this)
        }
    }
}