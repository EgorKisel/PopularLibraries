package com.example.popularlibraries.presenter

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.view.userdetails.UserDetailsView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val router: Router,
    private val user: GithubUser?
) : MvpPresenter<UserDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUser(user?.login)
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}