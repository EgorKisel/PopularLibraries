package com.example.popularlibraries.di

import com.example.popularlibraries.core.ConnectivityListener
import com.example.popularlibraries.model.database.UserDao
import com.example.popularlibraries.model.network.UsersApi
import com.example.popularlibraries.model.repository.Cacheable
import com.example.popularlibraries.model.repository.GithubRepository
import com.example.popularlibraries.model.repository.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        usersApi: UsersApi,
        userDao: UserDao,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable
    ): GithubRepository {
        return GithubRepositoryImpl(usersApi, userDao, networkStatus.statusSingle(), cacheable)
    }
}