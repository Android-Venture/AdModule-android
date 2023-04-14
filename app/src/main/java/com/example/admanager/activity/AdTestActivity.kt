package com.example.admanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View


import com.example.admanager.databinding.ActivityAdTestBinding
import com.sofit.adsimplementationhelper.ad_classes.AdmobBanner
import com.sofit.adsimplementationhelper.ad_classes.AdmobClass
import com.sofit.adsimplementationhelper.common.AdLoadCallback
import com.sofit.adsimplementationhelper.common.AdLogPrefs
import com.sofit.adsimplementationhelper.common.AdParamsPrefs
import com.sofit.adsimplementationhelper.common.ShowDialog

class AdTestActivity : AppCompatActivity() {

    val binding : ActivityAdTestBinding by lazy {
        ActivityAdTestBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AdmobBanner.showAdmobBanner(binding.bannerIncludeLayout.bannerAdFrame,this,AdParamsPrefs.getParams(this)!!,object :AdLoadCallback{
            override fun onLoaded() {
               binding.bannerIncludeLayout.bannerAdFrame.visibility = View.VISIBLE
            }

            override fun onFailed() {

            }

        },"Test Activity")
        AdmobClass.showNative(this,binding.nativeAdFrame,AdParamsPrefs.getParams(this)!!,"Test Activity")

        binding.showInterBtn.setOnClickListener {

            AdmobClass.showAdMobInter(this,AdParamsPrefs.getParams(this)!!,"Test Activity"){

            }
        }

        binding.showLogBtn.setOnClickListener {
            ShowDialog.showLogsDialog(this)
        }
    }
}