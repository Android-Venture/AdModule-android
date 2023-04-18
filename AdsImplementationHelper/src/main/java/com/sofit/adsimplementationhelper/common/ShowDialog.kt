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
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
        dialog.setContentView(logsDailogBinding.root)
        val adLog = AdLogPrefs.getLogs(context)
        logsDailogBinding.logsRecycler.layoutManager = LinearLayoutManager(context)
        logsDailogBinding.bannerReqTxt.setOnClickListener {
            logsDailogBinding.logsRecycler.adapter = adLog?.bannerRequests?.let { it1 ->
                LogsAdapter(
                    it1
                )
            }
        }

        logsDailogBinding.bannerImoTxt.setOnClickListener {
            logsDailogBinding.logsRecycler.adapter = adLog?.bannerImpressions?.let { it1 ->
                LogsAdapter(
                    it1
                )
            }
        }

        logsDailogBinding.nativeReqTxt.setOnClickListener {
            logsDailogBinding.logsRecycler.adapter = adLog?.nativeRequests?.let { it1 ->
                LogsAdapter(
                    it1
                )
            }
        }

        logsDailogBinding.nativeImpTxt.setOnClickListener {
            logsDailogBinding.logsRecycler.adapter = adLog?.nativeImpressions?.let { it1 ->
                LogsAdapter(
                    it1
                )
            }
        }

        logsDailogBinding.interstitialImpTxt.setOnClickListener {
            logsDailogBinding.logsRecycler.adapter = adLog?.interstitialImpressions?.let { it1 ->
                LogsAdapter(
                    it1
                )
            }
        }

        logsDailogBinding.interstitialReqTxt.setOnClickListener {

            logsDailogBinding.logsRecycler.adapter = adLog?.interstitialRequests?.let { it1 ->
                LogsAdapter(
                    it1
                )
            }
        }
        dialog.show()

    }
}