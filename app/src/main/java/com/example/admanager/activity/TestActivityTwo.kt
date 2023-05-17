package com.example.admanager.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.admanager.AD_FLOW
import com.example.admanager.R
import com.example.admanager.databinding.ActivityTestTwoBinding
import com.sofit.adsimplementationhelper.ad_classes.AdmobBanner
import com.sofit.adsimplementationhelper.ad_classes.AdmobClass
import com.sofit.adsimplementationhelper.common.AdLoadCallback
import com.sofit.adsimplementationhelper.common.AdParamsPrefs

class TestActivityTwo : AppCompatActivity() {

    val binding:ActivityTestTwoBinding by lazy {
        ActivityTestTwoBinding.inflate(layoutInflater)
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
                }

                override fun onFailed() {

                }

            }, it
            )
        }

        flow?.let {
            AdmobClass.showNative(this,binding.nativeAdFrame,
                it
            )
        }


        binding.showInterBtn.setOnClickListener {

            flow?.let { it1 ->
                AdmobClass.showAdMobInter(this, AdParamsPrefs.getParams(this)!!, it1){

                }
            }
        }

        binding.nextBtn.setOnClickListener {

            val intent = Intent(this,TestActivityThree::class.java)
            intent.putExtra(AD_FLOW,"Test Activity Two To Test Activity Three")
            startActivity(intent)
        }
    }


    override fun onBackPressed() {
        finish()
    }
}