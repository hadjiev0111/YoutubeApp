package com.amir.youtubeapp.di

import com.amir.youtubeapp.ui.activities.playlists.PlaylistsViewModel
import com.amir.youtubeapp.ui.activities.videos.VideosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules : Module = module {
    viewModel { PlaylistsViewModel(get()) }
    viewModel { VideosViewModel(get()) }
}