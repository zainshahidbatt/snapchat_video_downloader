package com.bluelock.snapchatdownloader.models

import com.google.gson.annotations.SerializedName

class SnapVideo {
    @SerializedName("error")
    var error = false

    @SerializedName("msg")
    var msg: String? = null

    @SerializedName("data")
    var data: Data? = null

    inner class Data {
        @SerializedName("id")
        var id: String? = null

        @SerializedName("title")
        var title: String? = null

        @SerializedName("timestamp")
        var timestamp = 0

        @SerializedName("description")
        var description: String? = null

        @SerializedName("thumbnail")
        var thumbnail: String? = null

        @SerializedName("age_limit")
        var age_limit = 0

        @SerializedName("_type")
        var _type: String? = null

        @SerializedName("url")
        var url: String? = null

        @SerializedName("ext")
        var ext: String? = null

        @SerializedName("thumbnails")
        var thumbnails: ArrayList<Thumbnail>? = null

        @SerializedName("duration")
        var duration = 0.0

        @SerializedName("view_count")
        var view_count = 0

        @SerializedName("original_url")
        var original_url: String? = null

        @SerializedName("webpage_url")
        var webpage_url: String? = null

        @SerializedName("webpage_url_basename")
        var webpage_url_basename: String? = null

        @SerializedName("webpage_url_domain")
        var webpage_url_domain: String? = null

        @SerializedName("extractor")
        var extractor: String? = null

        @SerializedName("extractor_key")
        var extractor_key: String? = null

        @SerializedName("playlist")
        var playlist: Any? = null

        @SerializedName("playlist_index")
        var playlist_index: Any? = null

        @SerializedName("display_id")
        var display_id: String? = null

        @SerializedName("fulltitle")
        var fulltitle: String? = null

        @SerializedName("duration_string")
        var duration_string: String? = null

        @SerializedName("upload_date")
        var upload_date: String? = null

        @SerializedName("requested_subtitles")
        var requested_subtitles: Any? = null

        @SerializedName("_has_drm")
        var _has_drm: Any? = null

        @SerializedName("protocol")
        var protocol: String? = null

        @SerializedName("resolution")
        var resolution: Any? = null

        @SerializedName("dynamic_range")
        var dynamic_range: String? = null

        @SerializedName("aspect_ratio")
        var aspect_ratio: Any? = null

        @SerializedName("http_headers")
        var http_headers: HttpHeaders? = null

        @SerializedName("video_ext")
        var video_ext: String? = null

        @SerializedName("audio_ext")
        var audio_ext: String? = null

        @SerializedName("format_id")
        var format_id: String? = null

        @SerializedName("format")
        var format: String? = null
    }

    inner class HttpHeaders {
        @SerializedName("User-Agent")
        var user_Agent: String? = null

        @SerializedName("accept")
        var accept: String? = null

        @SerializedName("Accept-Language")
        var accept_Language: String? = null

        @SerializedName("Sec-Fetch-Mode")
        var sec_Fetch_Mode: String? = null
    }

    inner class Thumbnail {
        @SerializedName("url")
        var url: String? = null

        @SerializedName("id")
        var id: String? = null
    }
}