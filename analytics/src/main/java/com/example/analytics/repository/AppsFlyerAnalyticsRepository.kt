package com.example.analytics.repository

import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerLib
import com.example.analytics.dependencies.Analytics
import com.example.analytics.events.AnalyticsEvent
import com.example.analytics.utils.toHashMap
import com.google.firebase.remoteconfig.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val TAG = "AnalyticsTAG"

class AppsFlyerAnalyticsRepository @Inject constructor(@ApplicationContext val context: Context) :
    Analytics {

    private val appsFlyerLib: AppsFlyerLib by lazy {
        AppsFlyerLib.getInstance()
            .init(null.toString(), null, context)
            .also { it.start(context) }
    }

    override fun logEvent(analyticsEvent: AnalyticsEvent) {
        if (BuildConfig.DEBUG) return
        val analyticsType = when (analyticsEvent) {
            is AnalyticsEvent.InterstitialAdEvent -> analyticsEvent.toAppsFlyerAdInterstitialEvent()
            is AnalyticsEvent.InAppPurchaseEvent -> analyticsEvent.toAppsFlyerPurchase()
            is AnalyticsEvent.RewardedAdEvent -> analyticsEvent.toAppsFlyerAdRewardedEvent()
            else -> null
        }

        analyticsType?.let {
            Log.d(
                TAG,
                "AppsFlyerAnalyticsRepository logEvent eventName....${it.event} arguments... ${it.args?.toHashMap()} "
            )
            appsFlyerLib.logEvent(
                context,
                it.event,
                it.args?.toHashMap()
            )
        }
    }

}