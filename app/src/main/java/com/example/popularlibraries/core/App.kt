package com.example.popularlibraries.core

import android.app.Application
import com.example.popularlibraries.di.AppComponent
import com.example.popularlibraries.di.AppModule
import com.example.popularlibraries.di.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}