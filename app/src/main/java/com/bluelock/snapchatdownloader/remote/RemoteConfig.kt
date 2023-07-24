package com.bluelock.snapchatdownloader.remote

import android.util.Log
import com.google.firebase.remoteconfig.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import javax.inject.Inject
import javax.inject.Singleton


private const val SHOW_APP_OPEN = "show_app_open_ad"
private const val SHOW_INTERSTITIAL = "show_interstitial"
private const val NATIVE_AD = "native_ad_on_all_features"


@Singleton
class RemoteConfig @Inject constructor() {

    private val defaults: Map<String, Any> = mapOf(
        SHOW_APP_OPEN to true,
        SHOW_INTERSTITIAL to true,
        NATIVE_AD to true,
    )

    private val config = FirebaseRemoteConfig.getInstance().apply {
        setConfigSettingsAsync(remoteConfigSettings {
            if (BuildConfig.DEBUG) minimumFetchIntervalInSeconds = 3600
        })
        setDefaultsAsync(defaults)
        fetchAndActivate().let {
            it.addOnCompleteListener {
                Log.d(
                    "jeje_remoteConfig",
                    "fetchAndActivate: ${all.mapValues { (_, v) -> v.asString() }}"
                )
            }
        }
    }

    val showAppOpenAd: Boolean
        get() = config[SHOW_APP_OPEN].asBoolean()

    val showInterstitial: Boolean
        get() = config[SHOW_INTERSTITIAL].asBoolean()

    val nativeAd: Boolean
        get() = config[NATIVE_AD].asBoolean()

}