package com.amir.youtubeapp

import android.app.Application
import com.amir.youtubeapp.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }

    // 1. В каждый активити вставить BaseUrl
    // 2. Отобразить количество видео в плейлисте
    // 3. Проработать кнопку пуск - Открывало первое видео в плейлисте
    // 4. Поменять время
    // 5. Room
    // 6. exoplayer


}