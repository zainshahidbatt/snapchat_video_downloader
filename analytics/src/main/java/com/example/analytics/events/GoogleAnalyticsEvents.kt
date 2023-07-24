package com.example.analytics.events

import com.example.analytics.utils.AnalyticsConstant.INAPP_PURCHASE
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics


sealed class GoogleAnalyticsEvents(
    val event: String?,
    val args: Bundle?
) {
    class ScreenView(clazz: Class<*>, name: String? = null) : AnalyticsEvent(
        FirebaseAnalytics.Event.SCREEN_VIEW,
        Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, name ?: clazz.simpleName)
            putString(FirebaseAnalytics.Param.SCREEN_CLASS, clazz.simpleName)
        }
    )

    class EventTriggered(
        eventId: String,
        tag: String? = null,
        source: Class<*>? = null
    ) : AnalyticsEvent(
        FirebaseAnalytics.Event.SELECT_CONTENT,
        Bundle().apply {
            putString(FirebaseAnalytics.Param.CONTENT_TYPE, "event")
            putString(FirebaseAnalytics.Param.ITEM_ID, eventId)
            source?.let { putString("event_source", it.simpleName) }
            tag?.let { putString("tag", it) }
        }
    )

    class InAppPurchaseEvent(
        private val status: String? = null,
        private val productId: String? = null,
        origin: String
    ) : AnalyticsEvent(
        INAPP_PURCHASE,
        Bundle().apply {
            status?.let { putString("status", status) }
            productId?.let { putString("productId", productId) }
            putString("origin", origin)
        }
    )

    class GoogleRawEvent(name: String, params: Bundle? = null) : AnalyticsEvent(name, params)
}