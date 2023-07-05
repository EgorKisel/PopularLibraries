package com.example.popularlibraries.presenter

import com.example.popularlibraries.core.App
import com.example.popularlibraries.model.network.ReposDto
import com.example.popularlibraries.view.userrepository.RepoUserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepoUserPresenter(private val repo: ReposDto?) :
    MvpPresenter<RepoUserView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        repo?.let { viewState.showRepo(it) }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}