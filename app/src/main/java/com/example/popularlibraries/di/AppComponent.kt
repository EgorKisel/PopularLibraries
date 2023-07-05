package com.example.popularlibraries.di

import com.example.popularlibraries.presenter.MainPresenter
import com.example.popularlibraries.presenter.RepoUserPresenter
import com.example.popularlibraries.presenter.UserDetailsPresenter
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
        RepositoryModule::class,
        CacheModule::class,
        RepoNetworkModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(detailsPresenter: UserDetailsPresenter)
    fun inject(repoUserPresenter: RepoUserPresenter)
}