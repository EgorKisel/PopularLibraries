package com.example.popularlibraries.presenter

import com.example.popularlibraries.common.UserScreen
import com.example.popularlibraries.common.UsersScreen
import com.example.popularlibraries.common.subscribeByDefault
import com.example.popularlibraries.core.App
import com.example.popularlibraries.model.repository.screen.UsersRepoScreen
import com.example.popularlibraries.view.users.UserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UserView>() {

    @Inject
    lateinit var repository: UsersRepoScreen

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        viewState.showLoading()
        repository.getUsers().subscribeByDefault()
            .subscribe({
                viewState.initList(it)
                viewState.hideLoading()
            }, {
                viewState.errorGetUser(it.message)
            })
    }

    fun openUserScreen(userLogin: String) {
        router.navigateTo(UserScreen(userLogin))
    }

    fun onBackPressed(): Boolean {
        router.backTo(UsersScreen)
        return true
    }
}