package com.example.popularlibraries.di

import com.example.popularlibraries.core.ConnectivityListener
import com.example.popularlibraries.model.repository.room.Cacheable
import com.example.popularlibraries.model.repository.GithubRepository
import com.example.popularlibraries.model.repository.GithubRepositoryImpl
import com.example.popularlibraries.model.repository.network.GithubApiRepo
import com.example.popularlibraries.model.repository.room.UserRepositoryRepo
import com.example.popularlibraries.model.repository.room.UsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        githubApiRepo: GithubApiRepo,
        usersRepo: UsersRepo,
        userRepositoryRepo: UserRepositoryRepo,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): GithubRepository {
        return GithubRepositoryImpl(
            githubApiRepo,
            usersRepo,
            userRepositoryRepo,
            networkStatus.statusSingle(),
            cacheable,
        )
    }
}