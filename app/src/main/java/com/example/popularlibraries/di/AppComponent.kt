package com.example.popularlibraries.di

import com.example.popularlibraries.presenter.MainPresenter
import com.example.popularlibraries.presenter.RepoUserPresenter
import com.example.popularlibraries.presenter.UsersPresenter
import com.example.popularlibraries.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
        DatabaseModule::class,
        NavigationModule::class,
        RepoNetworkModule::class,
        UsersScreenModule::class
    ]
)
interface AppComponent {
    fun userSubcomponent(): RepositorySubcomponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repoUserPresenter: RepoUserPresenter)
}