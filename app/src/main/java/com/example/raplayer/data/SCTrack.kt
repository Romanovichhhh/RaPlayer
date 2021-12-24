package com.example.raplayer.data

import com.google.gson.annotations.SerializedName




class SCTrack {

    class Track {
        @SerializedName("id")
        var id = 0

        @SerializedName("created_at")
        var created_at: String? = null

        @SerializedName("user_id")
        var user_id = 0

        @SerializedName("user")
        var user: User? = null

        @SerializedName("title")
        var title: String? = null

        @SerializedName("permalink")
        var permalink: String? = null

        @SerializedName("permalink_url")
        var permalink_url: String? = null

        @SerializedName("uri")
        var uri: String? = null

        @SerializedName("sharing")
        var sharing: String? = null

        @SerializedName("embeddable_by")
        var embeddable_by: String? = null

        @SerializedName("purchase_url")
        var purchase_url: String? = null

        @SerializedName("artwork_url")
        var artwork_url: String? = null

        @SerializedName("description")
        var description: String? = null

        @SerializedName("label")
        var label: User? = null

        @SerializedName("duration")
        var duration: Long = 0

        @SerializedName("genre")
        var genre: String? = null

        @SerializedName("tag_list")
        var tag_list: String? = null

        @SerializedName("label_id")
        var label_id = 0

        @SerializedName("label_name")
        var label_name: String? = null

        @SerializedName("release")
        var release: String? = null

        @SerializedName("release_day")
        var release_day = 0

        @SerializedName("release_month")
        var release_month = 0

        @SerializedName("release_year")
        var release_year = 0

        @SerializedName("streamable")
        var streamable = false

        @SerializedName("downloadable")
        var downloadable = false

        @SerializedName("state")
        var state: String? = null

        @SerializedName("license")
        var license: String? = null

        @SerializedName("track_type")
        var track_type: String? = null

        @SerializedName("waveform_url")
        var waveform_url: String? = null

        @SerializedName("download_url")
        var download_url: String? = null

        @SerializedName("stream_url")
        var stream_url: String? = null

        @SerializedName("video_url")
        var video_url: String? = null

        @SerializedName("bpm")
        var bpm = 0

        @SerializedName("commentable")
        var commentable = false

        @SerializedName("isrc")
        var isrc: String? = null

        @SerializedName("key_signature")
        var key_signature: String? = null

        @SerializedName("comment_count")
        var comment_count = 0

        @SerializedName("download_count")
        var download_count = 0

        @SerializedName("playback_count")
        var playback_count = 0

        @SerializedName("favoritings_count")
        var favoritings_count = 0

        @SerializedName("original_format")
        var original_format: String? = null

        @SerializedName("original_content_size")
        var original_content_size = 0
    }
}