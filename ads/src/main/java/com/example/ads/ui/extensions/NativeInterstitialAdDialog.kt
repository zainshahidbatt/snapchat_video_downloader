package com.example.ads.ui.extensions

import com.example.ads.GoogleManager
import com.example.ads.ui.dialog.NativeInterstitialAdDialog
import android.content.Context
import com.google.android.gms.ads.nativead.NativeAd
import kotlinx.coroutines.CoroutineScope


/**
 * Create a full screen dialog which shows a native interstitial ad.
 *
 * Use this method only if embedding the layout is not an option.
 * */
fun GoogleManager.createNativeInterstitialAdDialog(
    context: Context,
    scope: CoroutineScope,
    nativeAd: NativeAd? = createNativeFull(),
    onDismiss: () -> Unit
) {
    if (nativeAd == null) {
        onDismiss()
        return
    }

    NativeInterstitialAdDialog(
        context = context,
        ad = nativeAd,
        scope = scope
    ).apply { setOnDismissListener { onDismiss() } }.show()
}