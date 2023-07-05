package com.example.popularlibraries.di

import android.content.Context
import com.example.popularlibraries.model.database.GithubDB
import com.example.popularlibraries.model.database.dao.UserRepoDao
import com.example.popularlibraries.model.database.dao.UsersDao
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
    fun provideUserDao(database: GithubDB): UsersDao =
        database.usersDao()

    @Singleton
    @Provides
    fun userRepoDao(database: GithubDB): UserRepoDao =
        database.userRepoDao()
}