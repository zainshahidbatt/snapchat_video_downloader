package com.example.analytics.dependencies

import com.example.analytics.events.AnalyticsEvent

interface AppsFlyerAnalyticsHandler : Analytics {
    fun purchaseLogEvent(purchase: AnalyticsEvent)
}