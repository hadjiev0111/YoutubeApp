package com.amir.youtubeapp.ui.activities.playlists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amir.youtubeapp.App
import com.amir.youtubeapp.data.model.Info
import com.amir.youtubeapp.data.model.VideoInfo
import com.amir.youtubeapp.data.repository.Repository

class  PlaylistsViewModel(private val repository: Repository) : ViewModel() {

    fun setPlaylists() = repository.loadPlaylistsWithCoururines()


}