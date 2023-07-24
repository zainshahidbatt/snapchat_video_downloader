package ai.vyro.analytics.repository

import com.example.analytics.dependencies.Analytics
import com.example.analytics.events.AnalyticsEvent
import com.example.analytics.qualifiers.AmplitudeAnalytics
import com.example.analytics.qualifiers.AppsFlyerAnalytics
import com.example.analytics.qualifiers.GoogleAnalytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticsEventsBroadcast @Inject constructor(
    @AmplitudeAnalytics private val amplitudeAnalytics: Analytics,
    @GoogleAnalytics private val googleAnalytics: Analytics,
    @AppsFlyerAnalytics private val appsFlyerAnalytics: Analytics
) : Analytics {

    override fun logEvent(event: AnalyticsEvent) {
        when (event.event) {
            "INTERSTITIAL_AD,RATING,MEDIA_PERMISSION, ENHANCE_TUTORIAL, SCREEN_VIEW" -> return
        }
        amplitudeAnalytics.logEvent(event)
        googleAnalytics.logEvent(event)
        appsFlyerAnalytics.logEvent(event)

    }
}