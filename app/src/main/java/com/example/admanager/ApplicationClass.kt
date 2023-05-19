package com.example.admanager

import android.app.Application
import com.sofit.adsimplementationhelper.common.AdParamsPrefs
import com.sofit.adsimplementationhelper.common.Utils
import com.sofit.adsimplementationhelper.models.AdRequestParamModel

class ApplicationClass:Application() {


    override fun onCreate() {
        super.onCreate()

        AdParamsPrefs.saveParams(AdRequestParamModel("ca-app-pub-3940256099942544/6300978111","ca-app-pub-3940256099942544/2247696110","ca-app-pub-3940256099942544/1033173712","ca-app-pub-3940256099942544/3419835294",0,true,true,true,true),this)
       Utils.adNetworkInitialize(this)
    }
}