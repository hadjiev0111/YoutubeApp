package com.amir.youtubeapp.di

import com.amir.youtubeapp.data.remote.ApiClient.networkModule

val koinModules = listOf(
    networkModule,
    repoModules,
    viewModules

)