package com.ovoskop.marveltest.data.factory

import android.content.Context
import com.ovoskop.marveltest.utils.isNetworkConnected
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpFactory() {
    val client : OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()
}