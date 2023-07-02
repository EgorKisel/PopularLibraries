package com.example.popularlibraries.presenter

import com.example.popularlibraries.model.data.ReposDto
import com.example.popularlibraries.view.userrepository.RepoUserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepoUserPresenter(private val router: Router, private val repo: ReposDto?) :
    MvpPresenter<RepoUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repo?.let { viewState.showRepo(it) }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}