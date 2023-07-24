package com.bluelock.snapchatdownloader.di

import com.bluelock.snapchatdownloader.models.SnapVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DownloadAPIInterface {
    @GET("/snap.php")
    fun getSnapVideos(@Query("video") videoUrl: String?): Call<SnapVideo?>?
}