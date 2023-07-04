package com.example.popularlibraries.di

import com.example.popularlibraries.model.database.UserDao
import com.example.popularlibraries.model.repository.Cacheable
import com.example.popularlibraries.model.repository.RoomCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideCacheable(userDao: UserDao): Cacheable {
        return RoomCache(userDao)
    }
}