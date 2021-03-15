package com.amir.youtubeapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.amir.youtubeapp.`object`.Constants.API_KEY
import com.amir.youtubeapp.`object`.Constants.CHANNEL_ID
import com.amir.youtubeapp.`object`.Constants.PART
import com.amir.youtubeapp.data.model.VideoInfo
import com.amir.youtubeapp.data.remote.ApiClient
import retrofit2.Call
import retrofit2.Response

class Repository {

    var playlistsLiveData: MutableLiveData<MutableList<VideoInfo.Info>> = MutableLiveData()
    var videosLiveData: MutableLiveData<MutableList<VideoInfo.Info>> = MutableLiveData()


    fun loadPlaylists(): MutableLiveData<MutableList<VideoInfo.Info>> {
        ApiClient.getInstance().getPlaylists(PART, CHANNEL_ID, API_KEY)
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

    fun loadVideos(id: String): MutableLiveData<MutableList<VideoInfo.Info>> {
        ApiClient.getInstance().getVideos(PART, "", id, API_KEY)
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