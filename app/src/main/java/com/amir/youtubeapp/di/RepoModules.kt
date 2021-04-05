package com.amir.youtubeapp.di

import com.amir.youtubeapp.data.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules : Module = module{
    single { Repository(get()) }
}