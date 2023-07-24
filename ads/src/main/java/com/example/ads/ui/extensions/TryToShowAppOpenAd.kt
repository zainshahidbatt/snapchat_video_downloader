package com.example.ads.ui.extensions

import com.example.ads.GoogleManager
import com.example.ads.ui.OpenAppAdViewModel
import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback

private const val TAG = "TryToShowAppOpenAd"

fun Activity.tryToShowAppOpenAd(
    googleManager: GoogleManager,
    viewModel: OpenAppAdViewModel,
    action: ((adShown:Boolean) -> Unit)? = null
){
    if (viewModel.tryToShowAd) {
        val ad = googleManager.createAppOpenAd()
        ad?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                action?.invoke(true)
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                super.onAdFailedToShowFullScreenContent(p0)
                Log.e(TAG, "onAdFailedToShowFullScreenContent: ${p0.message}")
                action?.invoke(true)
            }
        }
        ad?.show(this)
    }
    viewModel.tryToShowAd = true
}