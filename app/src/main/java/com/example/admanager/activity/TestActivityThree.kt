package com.example.admanager.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.admanager.databinding.ActivityTestThreeBinding
import com.sofit.adsimplementationhelper.ad_classes.AdmobBanner
import com.sofit.adsimplementationhelper.ad_classes.AdmobClass
import com.sofit.adsimplementationhelper.common.AdLoadCallback
import com.sofit.adsimplementationhelper.common.AdParamsPrefs

class TestActivityThree : AppCompatActivity() {

    val  binding:ActivityTestThreeBinding by lazy {
        ActivityTestThreeBinding.inflate(layoutInflater)
    }
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        AdmobBanner.showAdmobBanner(binding.bannerIncludeLayout.bannerAdFrame,this,
            AdParamsPrefs.getParams(this)!!,object : AdLoadCallback {
                override fun onLoaded() {
                    binding.bannerIncludeLayout.bannerAdFrame.visibility = View.VISIBLE
                }

                override fun onFailed() {

                }

            },"Test Activity Three")
        AdmobClass.showNative(this,binding.nativeAdFrame, AdParamsPrefs.getParams(this)!!,"Test Activity Three")

        binding.showInterBtn.setOnClickListener {

            AdmobClass.showAdMobInter(this, AdParamsPrefs.getParams(this)!!,"Test Activity Three"){

            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}