package com.example.popularlibraries.di

import com.example.popularlibraries.core.ConnectivityListener
import com.example.popularlibraries.model.database.dao.UserRepoDao
import com.example.popularlibraries.model.database.dao.UsersDao
import com.example.popularlibraries.model.network.GithubApi
import com.example.popularlibraries.model.repository.Cacheable
import com.example.popularlibraries.model.repository.GithubRepository
import com.example.popularlibraries.model.repository.GithubRepositoryImpl
import com.example.popularlibraries.model.repository.network.GithubApiRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        githubApiRepo: GithubApiRepo,
        usersDao: UsersDao,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
        userRepoDao: UserRepoDao
    ): GithubRepository {
        return GithubRepositoryImpl(
            githubApiRepo,
            usersDao,
            networkStatus.statusSingle(),
            cacheable,
            userRepoDao
        )
    }
}