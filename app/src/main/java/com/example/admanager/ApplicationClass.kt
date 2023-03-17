package com.example.admanager

import android.app.Application
import com.sofit.adsimplementationhelper.common.Utils

class ApplicationClass:Application() {


    override fun onCreate() {
        super.onCreate()

       Utils.adNetworkInitialize(this,this)
    }
}