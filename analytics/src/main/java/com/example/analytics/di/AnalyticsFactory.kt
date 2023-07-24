package com.example.analytics.di

import ai.vyro.analytics.repository.AnalyticsEventsBroadcast
import com.example.analytics.dependencies.Analytics
import com.example.analytics.qualifiers.AmplitudeAnalytics
import com.example.analytics.qualifiers.AnalyticsBroadcast
import com.example.analytics.qualifiers.AppsFlyerAnalytics
import com.example.analytics.qualifiers.GoogleAnalytics
import com.example.analytics.repository.AmplitudeAnalyticsRepository
import com.example.analytics.repository.AppsFlyerAnalyticsRepository
import com.example.analytics.repository.FirebaseAnalyticsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AnalyticsFactory {

    @Binds
    @AmplitudeAnalytics
    fun providesAnalytics(amplitudeAnalyticsRepository: AmplitudeAnalyticsRepository): Analytics

    @Binds
    @GoogleAnalytics
    fun provideFirebaseAnalytics(firebaseAnalyticsRepository: FirebaseAnalyticsRepository): Analytics

    @Binds
    @AppsFlyerAnalytics
    fun providesAppsFlyerAnalytics(appsFlyerAnalyticsRepository: AppsFlyerAnalyticsRepository): Analytics

    @Binds
    @AnalyticsBroadcast
    fun providesAnalyticsWrapper(analyticsEventBroadcast: AnalyticsEventsBroadcast): Analytics


}