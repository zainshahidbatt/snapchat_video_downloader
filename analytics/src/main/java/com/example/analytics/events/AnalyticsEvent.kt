package com.example.analytics.events

import android.os.Bundle
import com.example.analytics.utils.AnalyticsConstant
import com.example.analytics.utils.AnalyticsConstant.APP_OPEN_AD
import com.example.analytics.utils.AnalyticsConstant.APP_USERS
import com.example.analytics.utils.AnalyticsConstant.BANNER_AD
import com.example.analytics.utils.AnalyticsConstant.BTN_DOWNLOAD
import com.example.analytics.utils.AnalyticsConstant.CLONE_STAMP_FEATURE
import com.example.analytics.utils.AnalyticsConstant.EDITING_FEATURE
import com.example.analytics.utils.AnalyticsConstant.EDITOR_BEFORE_AFTER
import com.example.analytics.utils.AnalyticsConstant.EDITOR_DESELECT
import com.example.analytics.utils.AnalyticsConstant.EDITOR_HOME_ICON
import com.example.analytics.utils.AnalyticsConstant.EDITOR_REDO
import com.example.analytics.utils.AnalyticsConstant.EDITOR_SAVE
import com.example.analytics.utils.AnalyticsConstant.EDITOR_SELECT
import com.example.analytics.utils.AnalyticsConstant.EDITOR_TAP
import com.example.analytics.utils.AnalyticsConstant.EDITOR_UNDO
import com.example.analytics.utils.AnalyticsConstant.ENHANCE_FEATURE_SELECTION
import com.example.analytics.utils.AnalyticsConstant.ENHANCE_TUTORIAL
import com.example.analytics.utils.AnalyticsConstant.GALLERY_CAMERA
import com.example.analytics.utils.AnalyticsConstant.GALLERY_IMAGE_SELECTION
import com.example.analytics.utils.AnalyticsConstant.HOME_EDIT
import com.example.analytics.utils.AnalyticsConstant.HOME_GET_FREE_TRIAL
import com.example.analytics.utils.AnalyticsConstant.HOME_PREMIUM_ICON
import com.example.analytics.utils.AnalyticsConstant.HOME_SETTINGS
import com.example.analytics.utils.AnalyticsConstant.IMAGE_SAVED
import com.example.analytics.utils.AnalyticsConstant.INAPP_PURCHASE
import com.example.analytics.utils.AnalyticsConstant.INAPP_YEARLY_BUTTON_EVENT
import com.example.analytics.utils.AnalyticsConstant.INTERSTITIAL_AD
import com.example.analytics.utils.AnalyticsConstant.LANGUAGE_SELECTED
import com.example.analytics.utils.AnalyticsConstant.LINK_TO_SEARCH
import com.example.analytics.utils.AnalyticsConstant.MEDIA_PERMISSION
import com.example.analytics.utils.AnalyticsConstant.RATING
import com.example.analytics.utils.AnalyticsConstant.RATING_CLOSE
import com.example.analytics.utils.AnalyticsConstant.RATING_PLAY_STORE
import com.example.analytics.utils.AnalyticsConstant.RATING_STAR
import com.example.analytics.utils.AnalyticsConstant.REMOVE_WM_PRO
import com.example.analytics.utils.AnalyticsConstant.REWARDED_AD
import com.example.analytics.utils.AnalyticsConstant.REW_AD_REMOVE_WM
import com.example.analytics.utils.AnalyticsConstant.REW_AD_SAVE_IN_HD
import com.example.analytics.utils.AnalyticsConstant.SAVE_BS_CLOSE
import com.example.analytics.utils.AnalyticsConstant.SAVE_BS_PDF
import com.example.analytics.utils.AnalyticsConstant.SAVE_BS_PNG
import com.example.analytics.utils.AnalyticsConstant.SAVE_BS_SHARE
import com.example.analytics.utils.AnalyticsConstant.SAVE_DIALOG_SAVE
import com.example.analytics.utils.AnalyticsConstant.SETTINGS_7_DAYS_TRIAL
import com.example.analytics.utils.AnalyticsConstant.SETTINGS_CONTACT_US
import com.example.analytics.utils.AnalyticsConstant.SETTINGS_FACEBOOK
import com.example.analytics.utils.AnalyticsConstant.SETTINGS_FEEDBACK
import com.example.analytics.utils.AnalyticsConstant.SETTINGS_INSTAGRAM
import com.example.analytics.utils.AnalyticsConstant.SETTINGS_MANAGE_SUBSCRIPTION
import com.example.analytics.utils.AnalyticsConstant.SETTINGS_RATE_US
import com.example.analytics.utils.AnalyticsConstant.SETTINGS_REMOVE_ADS
import com.example.analytics.utils.AnalyticsConstant.SETTINGS_TERMS_CONDITION

sealed class
AnalyticsEvent(
    val event: String? = null,
    val args: Bundle?
) {

    companion object {
        const val SCREEN_VIEW = "screenView"
    }

    class WMPurchaseEvent(
        private val status: String? = null,
        private val productId: String? = null,
        private val origin: String,
    ) : AnalyticsEvent(
        REMOVE_WM_PRO,
        Bundle().apply {
            status?.let { putString("status", it) }
            productId?.let { putString("productId", it) }
            putString("origin", origin)
        }
    )

    class AppOpenAdEvent(
        status: String
    ) : AnalyticsEvent(
        APP_OPEN_AD,
        Bundle().apply {
            putString("status", status)
        }
    )

    class RewAdDismissedEvent(
        status: String?
    ) : AnalyticsEvent(
        REW_AD_SAVE_IN_HD,
        Bundle().apply {
            putString("status", status)
        }
    )

    class PurchaseSuccess(
        status: String?
    ) : AnalyticsEvent(
        REMOVE_WM_PRO,
        Bundle().apply {
            putString("status", status)
        }
    )

    class AdDismissedOnRemoveWM(
        status: String?
    ) : AnalyticsEvent(
        REW_AD_REMOVE_WM,
        Bundle().apply {
            putString("status", status)
        }
    )

    class LanguageSelectionEvent(
        selectedLanguage: String,
    ) : AnalyticsEvent(
        LANGUAGE_SELECTED,
        Bundle().apply {
            putString("LanguageSelected", selectedLanguage)
        }
    )

    class NativeEvent(
        type: String,
        status: String
    ) : AnalyticsEvent(
        type,
        Bundle().apply {
            putString("status", status)
        }
    )


    class HomePremiumIconEvent(
        status: String
    ) : AnalyticsEvent(
        HOME_PREMIUM_ICON,
        Bundle().apply {
            putString("status", status)
        }
    )

    class HomeSettingsEvent(
        status: String
    ) : AnalyticsEvent(
        HOME_SETTINGS,
        Bundle().apply {
            putString("status", status)
        }
    )

    class HomeGetFreeTrialEvent(
        status: String
    ) : AnalyticsEvent(
        HOME_GET_FREE_TRIAL,
        Bundle().apply {
            putString("status", status)
        }
    )

    class HomeEditEvent(
        status: String
    ) : AnalyticsEvent(
        HOME_EDIT,
        Bundle().apply {
            putString("status", status)
        }
    )

    class GalleryCameraEvent(
        status: String
    ) : AnalyticsEvent(
        GALLERY_CAMERA,
        Bundle().apply {
            putString("status", status)
        }
    )


    class GalleryImageSelectionEvent(
        private val origin: String,
        private val isPremium: Boolean
    ) : AnalyticsEvent(
        event = GALLERY_IMAGE_SELECTION,
        args = Bundle().apply {
            putString("origin", origin)
            putBoolean("isPremium", isPremium)
        }
    )


    class EditorHomeIconEvent(
        status: String
    ) : AnalyticsEvent(
        EDITOR_HOME_ICON,
        Bundle().apply {
            putString("status", status)
        }
    )


    class EditorTapObjectEvent(
        status: String
    ) : AnalyticsEvent(
        EDITOR_TAP,
        Bundle().apply {
            putString("status", status)
        }
    )


    class BTNDownload(
        status: String
    ) : AnalyticsEvent(
        BTN_DOWNLOAD,
        Bundle().apply {
            putString("status", status)
        }
    )


    class LINK(
        status: String
    ) : AnalyticsEvent(
        LINK_TO_SEARCH,
        Bundle().apply {
            putString("status", status)
        }
    )


    class EditorDeselectEvent(
        status: String
    ) : AnalyticsEvent(
        EDITOR_DESELECT,
        Bundle().apply {
            putString("status", status)
        }
    )


    class EditorBeforeAfterEvent(
        status: String
    ) : AnalyticsEvent(
        EDITOR_BEFORE_AFTER,
        Bundle().apply {
            putString("status", status)
        }
    )


    class EditorRedoEvent(
        status: String
    ) : AnalyticsEvent(
        EDITOR_REDO,
        Bundle().apply {
            putString("status", status)
        }
    )


    class EditorUndoEvent(
        status: String
    ) : AnalyticsEvent(
        EDITOR_UNDO,
        Bundle().apply {
            putString("status", status)
        }
    )

    class CloneStampFeature(
        status: String
    ) : AnalyticsEvent(
        CLONE_STAMP_FEATURE,
        Bundle().apply {
            putString("status", status)
        }
    )

    class EditorSaveEvent(
        status: String
    ) : AnalyticsEvent(
        EDITOR_SAVE,
        Bundle().apply {
            putString("status", status)
        }
    )

    class EditorDrawEvent(
        type: String,
        status: String
    ) : AnalyticsEvent(
        type,
        Bundle().apply {
            putString("status", status)
        }
    )

    class RateStar(
        status: String,
        rating: Float
    ) : AnalyticsEvent(
        RATING_STAR,
        Bundle().apply {
            putString("status", status)
            putFloat("rating", rating)
        }
    )

    class RateClose(
        status: String,
    ) : AnalyticsEvent(
        RATING_CLOSE,
        Bundle().apply {
            putString("status", status)
        }
    )

    class RatePlayStore(
        status: String,
    ) : AnalyticsEvent(
        RATING_PLAY_STORE,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SaveDialogSaveEvent(
        status: String,
    ) : AnalyticsEvent(
        SAVE_DIALOG_SAVE,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SaveBottomSheetPNG(
        status: String,
        isPremium: Boolean
    ) : AnalyticsEvent(
        SAVE_BS_PNG,
        Bundle().apply {
            putString("status", status)
            putBoolean("Premium", isPremium)
        }
    )

    class SaveBottomSheetPDF(
        status: String,
        isPremium: Boolean
    ) : AnalyticsEvent(
        SAVE_BS_PDF,
        Bundle().apply {
            putString("status", status)
            putBoolean("Premium", isPremium)
        }
    )

    class SaveBottomSheetShare(
        status: String,
        shareOption: String
    ) : AnalyticsEvent(
        SAVE_BS_SHARE,
        Bundle().apply {
            putString("status", status)
            putString("Option", shareOption)
        }
    )

    class SaveBottomSheetClose(
        status: String,
    ) : AnalyticsEvent(
        SAVE_BS_CLOSE,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsSevenDaysTrialEvent(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_7_DAYS_TRIAL,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsRemoveAds(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_REMOVE_ADS,
        Bundle().apply {
            putString("status", status)
        }
    )


    class SettingsInstagram(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_INSTAGRAM,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsFacebook(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_FACEBOOK,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsRateUs(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_RATE_US,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsFeedback(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_FEEDBACK,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsContactUs(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_CONTACT_US,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsTermsConditions(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_TERMS_CONDITION,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsManageSubscription(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_MANAGE_SUBSCRIPTION,
        Bundle().apply {
            putString("status", status)
        }
    )

    class ScreenView(val clazz: Class<*>, val name: String? = null) : AnalyticsEvent(
        SCREEN_VIEW,
        Bundle().apply {
            putString("screenName", clazz.simpleName)
            name.let { putString("screenClass", name) }
        }
    )

    class InAppYearlyButtonEvent(
        status: String
    ) : AnalyticsEvent(
        INAPP_YEARLY_BUTTON_EVENT,
        Bundle().apply {
            putString("status", status)
        }
    )

    class InAppPurchaseEvent(
        private val status: String? = null,
        private val productId: String? = null,
        private val origin: String,
        private val trailPeriod: String? = null
    ) : AnalyticsEvent(
        INAPP_PURCHASE,
        Bundle().apply {
            status?.let { putString("status", it) }
            productId?.let { putString("productId", it) }
            putString("origin", origin)
        }
    ) {
        fun toAppsFlyerPurchase(): AppsFlyerAnalyticsEvent.AfSubscribe? {
            return trailPeriod?.let {
                AppsFlyerAnalyticsEvent.AfSubscribe(
                    contentId = productId,
                    afOrigin = origin,
                    trailPeriod = it
                )
            }
        }
    }

    class RatingEvent(
        private val status: Float
    ) : AnalyticsEvent(
        event = RATING,
        args = Bundle().apply {
            putFloat("status", status)
        }
    )

    class MediaPermissionEvent(
        private val status: Boolean,
        private val isPremium: Boolean,
        private val origin: Class<*>,
    ) : AnalyticsEvent(
        event = MEDIA_PERMISSION,
        args = Bundle().apply {
            putBoolean("status", status)
            putBoolean("isPremium", isPremium)
            putString("origin", origin.simpleName)
        }
    )

    class EditingFeatureEvent(
        private val status: String,
        private val editingFeature: String
    ) : AnalyticsEvent(
        event = EDITING_FEATURE,
        args = Bundle().apply {
            putString("status", status)
            putString("editingFeature", editingFeature)
        }
    )

    class ImageSavedEvent(
        private val origin: String,
        private val isPremium: Boolean
    ) : AnalyticsEvent(
        event = IMAGE_SAVED,
        args = Bundle().apply {
            putString("origin", origin)
            putBoolean("isPremium", isPremium)
        }
    )


    class EnhanceFeatureSelectionEvent(
        private val source: String,
        private val editingFeature: String
    ) : AnalyticsEvent(
        event = ENHANCE_FEATURE_SELECTION,
        args = Bundle().apply {
            putString("source", source)
            putString("editingFeature", editingFeature)
        }
    )

    class FeatureSelectionCommonEvent(eventName: String, category: String?, asset: String?) :
        AnalyticsEvent(
            event = eventName,
            args = Bundle().apply {
                putString("category", category)
                putString("asset", asset)
            }
        )

    class EnhanceTutorialEvent(
        private val source: String,
        private val status: String,
        private val editingFeature: String
    ) : AnalyticsEvent(
        event = ENHANCE_TUTORIAL,
        args = Bundle().apply {
            putString("source", source)
            putString("status", status)
            putString("editingFeature", editingFeature)
        }
    )

    class RewardedAdEvent(
        private val status: String,
        private val origin: String
    ) : AnalyticsEvent(
        event = REWARDED_AD,
        args = Bundle().apply {
            putString("status", status)
            putString("origin", origin)
        }
    ) {
        fun toAppsFlyerAdRewardedEvent(): AppsFlyerAnalyticsEvent.AfAdViewEvent {
            return AppsFlyerAnalyticsEvent.AfAdViewEvent(
                adType = AnalyticsConstant.REWARDED,
                afAdOrigin = origin
            )
        }
    }

    class InterstitialAdEvent(
        private val status: String,
        private val origin: String
    ) : AnalyticsEvent(
        event = INTERSTITIAL_AD,
        args = Bundle().apply {
            putString("status", status)
            putString("origin", origin)
        }
    ) {
        fun toAppsFlyerAdInterstitialEvent(): AppsFlyerAnalyticsEvent.AfAdViewEvent {
            return AppsFlyerAnalyticsEvent.AfAdViewEvent(
                adType = AnalyticsConstant.INTERSTITIAL,
                afAdOrigin = origin
            )
        }
    }

    class FeatureSelectionEventWithCustom(
        eventName: String,
        private val assetType: String? = null,
        private val category: String? = null,
        private val asset: String? = null,
        private val customType: String? = null
    ) : AnalyticsEvent(
        event = eventName,
        args = Bundle().apply {
            putString("assetType", assetType)
            category?.let { putString("category", category) }
            asset?.let { putString("asset", it) }
            customType?.let { putString("customType", it) }

        }
    )

    class FitSelectionEvent(
        eventName: String,
        category: String?,
        ratioCategory: String,
        ratio: String
    ) : AnalyticsEvent(
        event = eventName,
        args = Bundle().apply {
            category?.let { putString("category", it) }
            putString("ratioCategory", ratioCategory)
            putString("ratio", ratio)
        }
    )

    class UserEvent(userType: String) : AnalyticsEvent(
        event = APP_USERS,
        args = Bundle().apply { putString("user_types", userType) })

    class BannerAdEvent(
        private val status: String
    ) : AnalyticsEvent(
        event = BANNER_AD,
        args = Bundle().apply {
            putString("status", status)
        }
    )
}


