package com.amir.youtubeapp.ui.activities.videos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amir.youtubeapp.App
import com.amir.youtubeapp.data.model.PlaylistItem
import com.amir.youtubeapp.data.model.VideoInfo
import com.amir.youtubeapp.data.repository.Repository

class VideosViewModel(private val repository: Repository) : ViewModel() {

    var videos = MutableLiveData<MutableList<PlaylistItem>>()

    fun setVideos(id: String) {
        videos = repository.loadVideos(id)
    }
}