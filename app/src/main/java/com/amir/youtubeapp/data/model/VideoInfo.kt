package com.amir.youtubeapp.data.model

data class VideoInfo(val items: MutableList<Info>) {
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
}