package com.amir.youtubeapp.data.remote

import com.amir.youtubeapp.data.model.VideoInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String
    ): Call<VideoInfo>

    @GET("playlistItems")
    fun getVideos(
        @Query("part") part: String,
        @Query("pageToken") pageToken: String,
        @Query("playlistId") playlistId: String,
        @Query("key") key: String
    ): Call<VideoInfo>

}