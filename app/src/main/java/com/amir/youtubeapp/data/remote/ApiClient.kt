package com.amir.youtubeapp.data.remote

import com.amir.youtubeapp.`object`.Constants.BASE_URL
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val networkModule : Module = module {
        factory { provideApi(get()) }
        single { getInstance() }
    }

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideApi(retrofit: Retrofit) : YoutubeApi =
        retrofit.create(YoutubeApi::class.java)

}