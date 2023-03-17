package com.example.admanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.admanager.common.AdLogPrefs

import com.example.admanager.databinding.ActivityAdTestBinding
import com.sofit.adsimplementationhelper.ad_classes.AdmobBanner
import com.sofit.adsimplementationhelper.ad_classes.AdmobClass

class AdTestActivity : AppCompatActivity() {

    val binding : ActivityAdTestBinding by lazy {
        ActivityAdTestBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AdmobBanner.showAdmobBanner(binding.bannerIncludeLayout.bannerAdFrame,this)
        AdmobClass.showNative(this,binding.nativeAdFrame,"ca-app-pub-3940256099942544/2247696110")

        binding.showInterBtn.setOnClickListener {

            AdmobClass.showAdMobInter(this,"ca-app-pub-3940256099942544/8691691433"){

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