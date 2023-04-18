package com.example.admanager.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.admanager.R
import com.sofit.adsimplementationhelper.ad_classes.AdmobClass
import com.sofit.adsimplementationhelper.common.AdParamsPrefs

class MainActivity : AppCompatActivity() {



    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        AdmobClass.loadAdmobInterstitial(this,  AdParamsPrefs.getParams(this)!!,"Main Activity")
        AdmobClass.loadNativeAdmob(this, AdParamsPrefs.getParams(this)!!,"Main Activity")
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({

            startActivity(Intent(this,AdTestActivity::class.java))


            finish()
        }, 5000)


    }
}