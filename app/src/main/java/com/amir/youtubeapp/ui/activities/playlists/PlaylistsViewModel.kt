package com.amir.youtubeapp.ui.activities.playlists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amir.youtubeapp.App
import com.amir.youtubeapp.data.model.Info
import com.amir.youtubeapp.data.model.VideoInfo

class PlaylistsViewModel : ViewModel() {

    fun setPlaylists() = App.repository.loadPlaylistsWithCoururines()


}