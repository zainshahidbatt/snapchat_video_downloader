package com.example.ads.ui.binding

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.ads.R
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

@BindingAdapter("loadNativeAd")
fun NativeAdView.loadNativeAd(ad: NativeAd?) {
    ad?.let {
        visibility = View.VISIBLE
        mediaView = findViewById(R.id.mediaView)
        headlineView = findViewById(R.id.tvTitle)
        callToActionView = findViewById(R.id.btnCallToAction)
        val adIconView = findViewById<ImageView>(R.id.ad_icon)

        if (it.icon != null) {
            adIconView?.setImageDrawable(it.icon?.drawable)
        }

        mediaView?.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
        (headlineView as? TextView)?.text = it.headline
        (callToActionView as? Button)?.apply {
            visibility = if (it.callToAction != null) View.VISIBLE else View.INVISIBLE
            text = it.callToAction
        }
        setNativeAd(it)
    } ?: run { visibility = View.GONE }
}

@BindingAdapter("loadNativeAd")
fun NativeAdView.loadNativeAdWithoutCTA(ad: NativeAd?) {
    ad?.let {
        visibility = View.VISIBLE
        mediaView = findViewById(R.id.mediaView)
        headlineView = findViewById(R.id.tvTitle)
        callToActionView = findViewById(R.id.btnCallToAction)
        val adIconView = findViewById<ImageView>(R.id.ad_icon)

        if (it.icon != null) {
            adIconView?.setImageDrawable(it.icon?.drawable)
        }
        mediaView?.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
        (headlineView as? TextView)?.text = it.headline
        setNativeAd(it)
    } ?: run { visibility = View.GONE }

}