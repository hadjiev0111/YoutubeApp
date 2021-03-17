package com.amir.youtubeapp.ui.activities.videos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amir.youtubeapp.App
import com.amir.youtubeapp.data.model.PlaylistItem
import com.amir.youtubeapp.data.model.VideoInfo

class VideosViewModel : ViewModel() {

    var videos = MutableLiveData<MutableList<PlaylistItem>>()

    fun setVideos(id: String) {
        videos = App.repository.loadVideos(id)
    }
}