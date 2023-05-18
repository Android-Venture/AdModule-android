package com.example.admanager.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.admanager.AD_FLOW
import com.example.admanager.R
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
        val flow = intent.getStringExtra(AD_FLOW)


        flow?.let {
            AdmobBanner.showAdmobBanner(binding.bannerIncludeLayout.bannerAdFrame,this,
                AdParamsPrefs.getParams(this)!!,object : AdLoadCallback {
                    override fun onLoaded() {
                        binding.bannerIncludeLayout.bannerAdFrame.visibility = View.VISIBLE
                        binding.bannerIncludeLayout.adLoadingTxt.visibility = View.GONE
                    }

                    override fun onFailed() {
                        binding.bannerIncludeLayout.bannerAdFrame.visibility = View.GONE
                        binding.bannerIncludeLayout.adLoadingTxt.visibility = View.VISIBLE
                    }

                }, it
            )
        }
        flow?.let {
            AdmobClass.showNative(this,binding.nativeAdFrame,
                it
            , R.color.strong_grey,R.color.purple_200,R.color.purple_700)
        }

        binding.showInterBtn.setOnClickListener {

            flow?.let { it1 ->
                AdmobClass.showAdMobInter(this, AdParamsPrefs.getParams(this)!!, it1){

                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}