package com.example.popularlibraries.di

import com.example.popularlibraries.core.ConnectivityListener
import com.example.popularlibraries.model.repository.room.Cacheable
import com.example.popularlibraries.model.repository.screen.UserDetailsRepoScreen
import com.example.popularlibraries.model.repository.screen.UserDetailsRepoScreenImpl
import com.example.popularlibraries.model.repository.network.GithubApiRepo
import com.example.popularlibraries.model.repository.room.UserRepositoryRepo
import com.example.popularlibraries.model.repository.room.UsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UserDetailsScreenModule {

    @Singleton
    @Provides
    fun provideUserDetailsRepository(
        githubApiRepo: GithubApiRepo,
        usersRepo: UsersRepo,
        userRepositoryRepo: UserRepositoryRepo,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): UserDetailsRepoScreen {
        return UserDetailsRepoScreenImpl(
            githubApiRepo,
            usersRepo,
            userRepositoryRepo,
            networkStatus.statusSingle(),
            cacheable,
        )
    }
}