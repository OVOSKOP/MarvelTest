package com.ovoskop.marveltest.data.factory

class ServiceFactory(private val retrofitFactory: RetrofitFactory) {

    fun <T> create(serviceType: Class<T>) : T {
        return retrofitFactory
            .retrofit
            .create(serviceType)
    }

}