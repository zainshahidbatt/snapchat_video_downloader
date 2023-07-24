package com.example.analytics.repository

import com.example.analytics.dependencies.Analytics
import com.example.analytics.events.AnalyticsEvent
import com.example.analytics.utils.toJSONObject

import android.app.Application
import android.content.Context
import android.util.Log
import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import com.google.firebase.remoteconfig.BuildConfig

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AmplitudeAnalyticsRepository @Inject constructor(@ApplicationContext context: Context) :
    Analytics {

    val client: AmplitudeClient by lazy {
        Amplitude.getInstance()
            .initialize(
                context,
                null
            )
            .enableForegroundTracking(context as Application)
            .setFlushEventsOnClose(false)
    }

    override fun logEvent(event: AnalyticsEvent) {
        Log.d(
            "AnalyticsTAG",
            "AmplitudeAnalyticsRepository EventName :............ ${event.event} argument ${event.args}"
        )
        event.args?.toJSONObject()?.let {
            client.logEvent(event.event, it)
        } ?: client.logEvent(event.event)
    }
}