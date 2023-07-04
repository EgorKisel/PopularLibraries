package com.example.popularlibraries.core

import android.app.Application
import com.example.popularlibraries.di.AppComponent
import com.example.popularlibraries.di.AppModule
import com.example.popularlibraries.di.DaggerAppComponent
import com.example.popularlibraries.model.database.GithubDB
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App: Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }
}