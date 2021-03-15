package com.amir.youtubeapp

import android.app.Application
import com.amir.youtubeapp.data.repository.Repository

class App : Application() {

    companion object {
        lateinit var repository: Repository
    }

    override fun onCreate() {
        super.onCreate()
        repository = Repository()
    }


}