package com.example.ads


import android.content.Context
import com.example.ads.newStrategy.GoogleAppOpen
import com.example.ads.newStrategy.GoogleInterstitial
import com.example.ads.newStrategy.GoogleNativeFull
import com.example.ads.newStrategy.GoogleNativeSmall
import com.example.ads.newStrategy.GoogleRewarded
import com.example.ads.newStrategy.types.GoogleInterstitialType
import com.example.ads.newStrategy.types.GoogleRewardedType
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.rewarded.RewardedAd
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private var googleNativeSmall: GoogleNativeSmall? = null
    private var googleInterstitial: GoogleInterstitial? = null
    private var googleAppOpen: GoogleAppOpen? = null
    private var googleNativeFull: GoogleNativeFull? = null
    private var googleRewarded: GoogleRewarded? = null


    private val testDeviceIds: List<String> = listOf(
        AdRequest.DEVICE_ID_EMULATOR,
        "2799362CE96AAA7736F188963E9E156C"
    )

    fun init() {


        MobileAds.initialize(context)

        if (BuildConfig.DEBUG)
            MobileAds.setRequestConfiguration(
                RequestConfiguration.Builder()
                    .setTestDeviceIds(testDeviceIds)
                    .build()
            )

        googleNativeSmall = GoogleNativeSmall(context);
        googleInterstitial = GoogleInterstitial(context);
        googleAppOpen = GoogleAppOpen(context);
        googleNativeFull = GoogleNativeFull(context)
        googleRewarded = GoogleRewarded(context)


    }

    fun createRewardedAd(type: GoogleRewardedType? = null): RewardedAd? {

        if (type == GoogleRewardedType.HIGH) {
            return googleRewarded?.getHighFloorAd(context);
        } else if (type == GoogleRewardedType.MEDIUM) {
            return googleRewarded?.getMediumAd(context);
        } else {
            return googleRewarded?.getDefaultAd(context);
        }
    }

    fun createInterstitialAd(type: GoogleInterstitialType): InterstitialAd? {


        return when (type) {
            GoogleInterstitialType.HIGH -> {
                googleInterstitial?.getHighFloorAd(context);
            }

            GoogleInterstitialType.MEDIUM -> {
                googleInterstitial?.getMediumAd(context);
            }

            else -> {
                googleInterstitial?.getDefaultAd(context);
            }
        }
    }

    fun createNativeFull(): NativeAd? {
        return googleNativeFull?.getDefaultAd(context);
    }

    fun createNativeAdSmall(): NativeAd? {
        return googleNativeSmall?.getDefaultAd(context);
    }

    fun createAppOpenAd(): AppOpenAd? {
        return googleAppOpen?.getAd(context);
    }




}