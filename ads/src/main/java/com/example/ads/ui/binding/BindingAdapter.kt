package com.example.ads.ui.binding

import androidx.databinding.BindingAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

private const val TAG = "GoogleBannerAd"

@BindingAdapter("loadAd")
fun AdView.loadAd(load: Boolean) {
    if (load) {
        loadAd(AdRequest.Builder().build())
    }

}