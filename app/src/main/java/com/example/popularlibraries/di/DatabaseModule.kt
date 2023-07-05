package com.example.popularlibraries.di

import android.content.Context
import com.example.popularlibraries.model.database.GithubDB
import com.example.popularlibraries.model.database.dao.UserRepoDao
import com.example.popularlibraries.model.database.dao.UsersDao
import com.example.popularlibraries.model.repository.room.Cacheable
import com.example.popularlibraries.model.repository.room.CacheableImpl
import com.example.popularlibraries.model.repository.room.UserRepositoryRepo
import com.example.popularlibraries.model.repository.room.UserRepositoryRepoImpl
import com.example.popularlibraries.model.repository.room.UsersRepo
import com.example.popularlibraries.model.repository.room.UsersRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): GithubDB =
        GithubDB.create(context)

    @Singleton
    @Provides
    fun provideUserDao(database: GithubDB): UsersRepo =
        UsersRepoImpl(database.usersDao())

    @Singleton
    @Provides
    fun userRepoDao(database: GithubDB): UserRepositoryRepo =
        UserRepositoryRepoImpl(database.userRepoDao())

    @Singleton
    @Provides
    fun cacheable(usersRepo: UsersRepo, userRepositoryRepo: UserRepositoryRepo): Cacheable {
        return CacheableImpl(usersRepo, userRepositoryRepo)
    }
}