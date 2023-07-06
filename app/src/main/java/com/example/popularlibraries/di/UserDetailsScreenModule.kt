package com.example.popularlibraries.di

import com.example.popularlibraries.core.ConnectivityListener
import com.example.popularlibraries.model.repository.network.GithubApiRepo
import com.example.popularlibraries.model.repository.room.Cacheable
import com.example.popularlibraries.model.repository.room.UserRepositoryRepo
import com.example.popularlibraries.model.repository.room.UsersRepo
import com.example.popularlibraries.model.repository.screen.UserDetailsRepoScreen
import com.example.popularlibraries.model.repository.screen.UserDetailsRepoScreenImpl
import com.example.popularlibraries.presenter.UserDetailsPresenter
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RepositoryScope


@Module
open class UserDetailsScreenModule {
    @Provides
    @RepositoryScope
    fun provideUserDetailsRepository(
        githubApiRepo: GithubApiRepo,
        userRepositoryRepo: UserRepositoryRepo,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): UserDetailsRepoScreen {
        return UserDetailsRepoScreenImpl(
            githubApiRepo,
            userRepositoryRepo,
            networkStatus.statusSingle(),
            cacheable,
        )
    }
}

@RepositoryScope
@Subcomponent(
    modules = [
        UserDetailsScreenModule::class
    ]
)

interface RepositorySubcomponent {
    fun inject(userPresenter: UserDetailsPresenter)
}