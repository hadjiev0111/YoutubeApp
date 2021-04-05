package com.amir.youtubeapp.data.model

import com.google.gson.annotations.SerializedName

data class VideoInfo(
    @SerializedName("nextPageToken")
    val nextPageToken: String? = null,
    val items: MutableList<PlaylistItem>
)

data class PlaylistItem(
    var id: String,
    var snippet: Snippet? = null,
    var playlistId: String? = null,
    var contentDetails: ContentDetails? = null,
    var startTime: Float = 0f,
    var timeString: String = ""
)
{
    override fun toString(): String {
        return "VideoItem(id='$id', snippet=$snippet, playlistId=$playlistId, contentDetails=$contentDetails, startTime=$startTime)"
    }
}

data class Info(
    var id: String? = null,
    var snippet: Snippet? = null,
    var contentDetails: ContentDetails? = null
)

data class ContentDetails(
    var itemCount: Int? = null,
    var videoId: String? = null
)

data class Snippet(
    var channelId: String? = null,
    var title: String? = null,
    var description: String? = null,
    var thumbnails: Thumbnails? = null,
    var channelTitle: String? = null,
    var publishedAt: String? = null
)

data class Thumbnails(
    var medium: Medium? = null
)

class Medium(
    var url: String? = null,
    var width: String? = null,
    var height: String? = null
)