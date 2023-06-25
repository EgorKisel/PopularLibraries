package com.example.popularlibraries.presenter

import com.example.popularlibraries.common.UserScreen
import com.example.popularlibraries.common.UsersScreen
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.repository.GithubRepository
import com.example.popularlibraries.view.users.UserView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    private val repository: GithubRepository,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        repository.getUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.initList(it)
                viewState.hideLoading()
            }, {
                viewState.errorGetUser(it.message)
            })
    }

    fun openUserScreen(user: GithubUser) {
        router.navigateTo(UserScreen(user))
    }

    fun onBackPressed(): Boolean {
        router.backTo(UsersScreen)
        return true
    }
}