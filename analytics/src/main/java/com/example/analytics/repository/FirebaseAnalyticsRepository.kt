package com.example.analytics.repository

import com.example.analytics.dependencies.Analytics
import com.example.analytics.events.AnalyticsEvent
import android.content.Context
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FirebaseAnalyticsRepository @Inject constructor(
    @ApplicationContext context: Context
) : Analytics {

    private val analytics = FirebaseAnalytics.getInstance(context)

    override fun logEvent(analyticsEvent: AnalyticsEvent) {
        Log.d(
            "AnalyticsTAG",
            "FirebaseAnalyticsRepository eventName....${analyticsEvent.event} arguments... ${analyticsEvent.args} "
        )

        analyticsEvent.event?.let { eventName ->
            analytics.logEvent(eventName, analyticsEvent.args)

        }
    }


}
