package com.example.ads.ui.binding

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.ads.databinding.LayoutAdmobNativeMaxBinding
import com.google.android.gms.ads.nativead.NativeAd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun LayoutAdmobNativeMaxBinding?.show(
    coroutineScope: CoroutineScope,
    nativeAd: NativeAd,
    onDismiss: () -> Unit
) {
    if (this == null) {
        onDismiss()
        return
    }

    coroutineScope.launch {
        cvClose.setOnClickListener(null)
        ivClose.isVisible = false
        tvCounter.isVisible = true

        for (i in 5 downTo 1) {
            tvCounter.text = i.toString()
            delay(1000)
        }

        tvCounter.isVisible = false
        ivClose.isVisible = true
        cvClose.setOnClickListener {
            onDismiss()
            nativeAd.destroy()
            root.isVisible = false
        }
    }

    nativeView.mediaView = mediaView
    nativeView.headlineView = adHeadline.apply {
        text = nativeAd.headline
    }
    nativeView.callToActionView = adCallToAction.apply {
        usingNullable(nativeAd.callToAction) { text = it }
    }
    nativeView.iconView = adIcon.apply {
        usingNullable(nativeAd.icon) { setImageDrawable(it.drawable) }
    }

    nativeView.setNativeAd(nativeAd)
    root.isVisible = true
}

private inline fun <V : View, T> V.usingNullable(parameter: T?, action: V.(T) -> Unit) {
    isInvisible = parameter == null
    if (!isInvisible) action(parameter!!)
}