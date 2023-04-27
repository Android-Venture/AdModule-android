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

        logsDailogBinding.logsRecycler.adapter = adLog?.logs?.let { it1 ->
            LogsAdapter(
                it1
            )
        }
        dialog.show()

    }
}