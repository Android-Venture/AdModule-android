package com.sofit.adsimplementationhelper.common

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.sofit.adsimplementationhelper.adapter.LogsAdapter
import com.sofit.adsimplementationhelper.databinding.LogsDailogBinding

object ShowDialog {


    fun showLogsDialog(context: Context) {

        val logsDailogBinding = LogsDailogBinding.inflate(LayoutInflater.from(context))

        val dialog = Dialog(context,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
        dialog.setContentView(logsDailogBinding.root)

        val ad_log = AdLogPrefs.getLogs(context)
        logsDailogBinding.logsRecycler.layoutManager = LinearLayoutManager(context)

        logsDailogBinding.bannerReqTxt.setOnClickListener {

            logsDailogBinding.logsRecycler.adapter = LogsAdapter(ad_log!!.bannerRequest)


        }

        logsDailogBinding.bannerImoTxt.setOnClickListener {
            logsDailogBinding.logsRecycler.adapter = LogsAdapter(ad_log!!.bannerImpression)
        }

        logsDailogBinding.nativeReqTxt.setOnClickListener {
            logsDailogBinding.logsRecycler.adapter = LogsAdapter(ad_log!!.nativeRequest)
        }

        logsDailogBinding.nativeImpTxt.setOnClickListener {
            logsDailogBinding.logsRecycler.adapter = LogsAdapter(ad_log!!.nativeImpression)
        }

        logsDailogBinding.interstitialImpTxt.setOnClickListener {
            logsDailogBinding.logsRecycler.adapter = LogsAdapter(ad_log!!.interstitialImpression)
        }

        logsDailogBinding.interstitialReqTxt.setOnClickListener {

            logsDailogBinding.logsRecycler.adapter = LogsAdapter(ad_log!!.interstitialRequest)

        }


        dialog.show()

    }
}