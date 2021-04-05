package com.amir.youtubeapp.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.bumptech.glide.Glide

fun ImageView.setImageFromUrl(url: String?) {
    Glide.with(this).load(url).into(this)
}

fun Context.isConnected(): Boolean {
    val connectivityManager =
        this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                return true
            }
        }
    }
    return false
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, LENGTH_LONG).show()
}