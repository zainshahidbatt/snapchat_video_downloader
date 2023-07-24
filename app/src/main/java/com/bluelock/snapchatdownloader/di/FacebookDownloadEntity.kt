package com.bluelock.snapchatdownloader.di

import android.provider.BaseColumns

object FacebookDownloadEntity : BaseColumns {
    const val TABLE_NAME = "facebook_downloads"
    const val COLUMN_IS_WATERMARKED = "isWatermarked"
    const val COLUMN_OUTPUT_PATH = "outputPath"
    const val COLUMN_FILE_NAME = "fileName"
    const val COLUMN_DOWNLOAD_ID = "downloadId"
    const val COLUMN_FILE_URI = "fileUri"
    const val COLUMN_STATE = "state"
    const val COLUMN_VIDEO_SOURCE = "source"
    const val COLUMN_DOWNLOAD_TIME = "time"
    const val INDEX_DOWNLOAD_ID = 0
    const val INDEX_FILE_NAME = 1
    const val INDEX_FILE_URI = 2
    const val INDEX_OUTPUT_PATH = 3
    const val INDEX_IS_WATERMARKED = 4
    const val INDEX_STATE = 5
    const val INDEX_VIDEO_SOURCE = 6
    const val INDEX_DOWNLOAD_TIME = 7
}