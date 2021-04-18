package com.ovoskop.marveltest.data.factory

import com.ovoskop.marveltest.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory(httpFactory: OkHttpFactory) {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpFactory.client)
        .build()
}