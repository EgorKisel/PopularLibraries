package com.example.popularlibraries.di

import com.example.popularlibraries.model.database.dao.UserRepoDao
import com.example.popularlibraries.model.database.dao.UsersDao
import com.example.popularlibraries.model.repository.Cacheable
import com.example.popularlibraries.model.repository.RoomCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideCacheable(usersDao: UsersDao, repoDao: UserRepoDao): Cacheable {
        return RoomCache(usersDao, repoDao)
    }
}