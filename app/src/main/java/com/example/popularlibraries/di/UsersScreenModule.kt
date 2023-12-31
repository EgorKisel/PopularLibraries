package com.example.popularlibraries.di

import com.example.popularlibraries.core.ConnectivityListener
import com.example.popularlibraries.model.repository.network.GithubApiRepo
import com.example.popularlibraries.model.repository.room.Cacheable
import com.example.popularlibraries.model.repository.room.UsersRepo
import com.example.popularlibraries.model.repository.screen.UsersRepoScreen
import com.example.popularlibraries.model.repository.screen.UsersRepoScreenImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UsersScreenModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        githubApiRepo: GithubApiRepo,
        usersRepo: UsersRepo,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable
    ): UsersRepoScreen {
        return UsersRepoScreenImpl(
            githubApiRepo,
            usersRepo,
            networkStatus.statusSingle(),
            cacheable,
        )
    }
}