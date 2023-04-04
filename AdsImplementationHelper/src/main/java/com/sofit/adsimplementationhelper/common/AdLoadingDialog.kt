package com.sofit.adsimplementationhelper.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.sofit.adsimplementationhelper.R
import com.sofit.adsimplementationhelper.databinding.AdLoadingDialogBinding


object AdLoadingDialog {

    //TODO : Avoid these type of warnings and problems and make a practice to not leave any minors linting issues like this
    var dialog:Dialog ? = null

    fun showDialog(context: Context) {
        val binding = AdLoadingDialogBinding.inflate(LayoutInflater.from(context))
        dialog = Dialog(context)

        //TODO: Never use bang operator instead use elvis operator along with null safe
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