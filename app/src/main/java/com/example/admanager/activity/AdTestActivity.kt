package com.example.admanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


import com.example.admanager.databinding.ActivityAdTestBinding
import com.sofit.adsimplementationhelper.ad_classes.AdmobBanner
import com.sofit.adsimplementationhelper.ad_classes.AdmobClass
import com.sofit.adsimplementationhelper.common.AdLoadCallback
import com.sofit.adsimplementationhelper.common.AdLogPrefs
import com.sofit.adsimplementationhelper.common.AdParamsPrefs

class AdTestActivity : AppCompatActivity() {

    val binding : ActivityAdTestBinding by lazy {
        ActivityAdTestBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AdmobBanner.showAdmobBanner(binding.bannerIncludeLayout.bannerAdFrame,this,AdParamsPrefs.getParams(this)!!,object :AdLoadCallback{
            override fun onLoaded() {

            }

            override fun onFailed() {

            }

        })
        AdmobClass.showNative(this,binding.nativeAdFrame,AdParamsPrefs.getParams(this)!!)

        binding.showInterBtn.setOnClickListener {

            AdmobClass.showAdMobInter(this,AdParamsPrefs.getParams(this)!!){

            }
        }

        binding.showLogBtn.setOnClickListener {
            val ad_log = AdLogPrefs.getLogs(this)

            Log.d("AD_LOG","BANNER_REQ "+ad_log!!.banner_request.toString())
            Log.d("AD_LOG","BANNER_IMP "+ad_log.banner_impression.toString())
            Log.d("AD_LOG","NATIVE_REQ "+ad_log.native_request.toString())
            Log.d("AD_LOG","NATIVE_IMP "+ad_log.native_impression.toString())
            Log.d("AD_LOG","INTERSTITIAL_REQ "+ad_log.interstitial_request.toString())
            Log.d("AD_LOG","INTERSTITIAL_IMP "+ad_log.interstitial_impression.toString())
        }
    }
}