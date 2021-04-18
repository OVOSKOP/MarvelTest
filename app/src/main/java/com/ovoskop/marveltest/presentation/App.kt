package com.ovoskop.marveltest.presentation

import android.app.Application
import com.ovoskop.marveltest.utils.dbModule
import com.ovoskop.marveltest.utils.listModule
import com.ovoskop.marveltest.utils.mainModule
import com.ovoskop.marveltest.utils.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(mainModule, networkModule, listModule, dbModule))
        }

    }

}