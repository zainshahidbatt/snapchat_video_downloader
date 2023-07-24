package com.bluelock.snapchatdownloader.models

class FVideo {
    var isWatermarked = false

    //Initial download path
    var outputPath: String? = null
    var fileName: String? = null
    var downloadId: Long = 0

    //Where file actually saved in memory after processing
    var fileUri: String? = null
    var state = 0
    var videoSource = 0
    var downloadTime: Long

    constructor(
        outputPath: String?,
        fileName: String?,
        downloadId: Long,
        isWatermarked: Boolean,
        downloadTime: Long
    ) {
        this.outputPath = outputPath
        this.fileName = fileName
        this.downloadId = downloadId
        this.isWatermarked = isWatermarked
        this.downloadTime = downloadTime
    }

    constructor(downloadTime: Long) {
        this.downloadTime = downloadTime
    }

    companion object {
        const val DOWNLOADING = 1
        const val PROCESSING = 2
        const val COMPLETE = 3
        const val FACEBOOK = 1
        const val SNAPCHAT = 4

    }
}