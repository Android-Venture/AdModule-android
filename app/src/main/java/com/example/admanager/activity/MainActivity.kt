package com.example.admanager.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.admanager.R
import com.example.admanager.ad_classes.AdmobClass

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AdmobClass.load_native_admob1(this,"ca-app-pub-3940256099942544/2247696110")
        AdmobClass.load_native_admob2(this,"ca-app-pub-3940256099942544/2247696110")

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({

            startActivity(Intent(this,AdTestActivity::class.java))


            finish()
        }, 5000)


    }
}