package com.example.analytics.events

import android.os.Bundle
import com.example.analytics.utils.AnalyticsConstant

sealed class AppsFlyerAnalyticsEvent(event: String, args: Bundle) :
    AnalyticsEvent(event = event, args = args) {

    class AfAdViewEvent(adType: String, afAdOrigin: String) : AppsFlyerAnalyticsEvent(
        event = AnalyticsConstant.AF_AD_VIEW,
        args = Bundle().apply {
            putString("ad_type", adType)
            putString("af_ad_origin", afAdOrigin)
            putString("af_adrev_ad_type", AnalyticsConstant.ADVERTISE_ID)
        }
    )

    class AfSubscribe(contentId: String?, afOrigin: String, trailPeriod: String?) :
        AppsFlyerAnalyticsEvent(
            event = AnalyticsConstant.AF_SUBSCRIBE,
            args = Bundle().apply {
                putString("af_content_id", contentId)
                putString("af_purchase_origin", afOrigin)
                putString("af_trial_period", trailPeriod)
            }
        )

}