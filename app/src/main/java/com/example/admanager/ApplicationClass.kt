package com.example.admanager

import android.app.Application
import com.sofit.adsimplementationhelper.ad_classes.AdmobClass
import com.sofit.adsimplementationhelper.ad_classes.AppOpenManager
import com.sofit.adsimplementationhelper.common.Utils

class ApplicationClass:Application() {
    var appOpenManager: AppOpenManager? = null

    override fun onCreate() {
        super.onCreate()

       Utils.adNetworkInitialize(this,this)
    }
}