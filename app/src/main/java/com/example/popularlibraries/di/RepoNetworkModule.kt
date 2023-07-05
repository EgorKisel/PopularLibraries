package com.example.popularlibraries.di

import com.example.popularlibraries.model.network.GithubApi
import com.example.popularlibraries.model.repository.network.GithubApiRepo
import com.example.popularlibraries.model.repository.network.GithubApiRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepoNetworkModule {

    @Singleton
    @Provides
    fun provideRepoNetwork(githubApi: GithubApi): GithubApiRepo {
        return GithubApiRepoImpl(githubApi)
    }
}