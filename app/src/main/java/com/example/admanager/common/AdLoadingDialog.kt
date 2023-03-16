package com.example.admanager.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.example.admanager.R
import com.example.admanager.databinding.AdLoadingDialogBinding

object AdLoadingDialog {

    var dialog:Dialog ? = null

    fun showDialog(context: Context) {
        val binding = AdLoadingDialogBinding.inflate(LayoutInflater.from(context))
        dialog = Dialog(context)
        dialog!!.setContentView(binding.root)
        dialog!!.setCancelable(false)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Glide.with(context).load(R.drawable.loading_gif).into(binding.loadingImage)
        dialog!!.show()
    }

   fun hideDialog() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }
}