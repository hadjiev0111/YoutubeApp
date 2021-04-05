package com.amir.youtubeapp.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.amir.youtubeapp.`object`.Constants.API_KEY
import com.amir.youtubeapp.`object`.Constants.CHANNEL_ID
import com.amir.youtubeapp.`object`.Constants.PART
import com.amir.youtubeapp.data.model.Info
import com.amir.youtubeapp.data.model.PlaylistItem
import com.amir.youtubeapp.data.model.VideoInfo
import com.amir.youtubeapp.data.remote.ApiClient
import com.amir.youtubeapp.data.remote.YoutubeApi
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Response

class Repository(private val youtubeApi: YoutubeApi) {

    var playlistsLiveData: MutableLiveData<MutableList<PlaylistItem>> = MutableLiveData()
    var videosLiveData: MutableLiveData<MutableList<PlaylistItem>> = MutableLiveData()


    fun loadPlaylists(): MutableLiveData<MutableList<PlaylistItem>> {
        youtubeApi.getPlaylists(PART, CHANNEL_ID, null, API_KEY)
            .enqueue(object : retrofit2.Callback<VideoInfo> {
                override fun onResponse(
                    call: Call<VideoInfo>,
                    response: Response<VideoInfo>
                ) {
                    val data = response.body()
                    val list = data!!.items
                    playlistsLiveData.value = list
                }

                override fun onFailure(call: Call<VideoInfo>, t: Throwable) {
                }
            })
        return playlistsLiveData
    }

    fun loadPlaylistsWithCoururines() = liveData(Dispatchers.IO) {
        val data = getPlaylists()
        emit(data)
    }

    private suspend fun getPlaylists(playlistItems: MutableList<PlaylistItem> = mutableListOf(), nextPageToken: String? = null): MutableList<PlaylistItem> {
        val data = youtubeApi.getPlaylistsWithCourutines(PART, CHANNEL_ID, nextPageToken, API_KEY)
        playlistItems.addAll(data.items)
        return if (data.nextPageToken == null) playlistItems
        else getPlaylists(playlistItems = playlistItems, nextPageToken = data.nextPageToken)
    }

    fun loadVideos(id: String): MutableLiveData<MutableList<PlaylistItem>> {
        youtubeApi.getVideos(PART, "", id, API_KEY)
            .enqueue(object : retrofit2.Callback<VideoInfo> {
                override fun onResponse(call: Call<VideoInfo>, response: Response<VideoInfo>) {
                    val data = response.body()
                    val list = data!!.items
                    videosLiveData.value = list
                }

                override fun onFailure(call: Call<VideoInfo>, t: Throwable) {
                }
            })
        return videosLiveData
    }
}