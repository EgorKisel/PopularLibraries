package com.example.popularlibraries.presenter

import android.util.Log
import com.example.popularlibraries.common.RepoScreen
import com.example.popularlibraries.common.UserScreen
import com.example.popularlibraries.common.disposableBy
import com.example.popularlibraries.common.subscribeByDefault
import com.example.popularlibraries.core.App
import com.example.popularlibraries.di.RepositorySubcomponent
import com.example.popularlibraries.model.network.ReposDto
import com.example.popularlibraries.model.repository.screen.UserDetailsRepoScreen
import com.example.popularlibraries.view.userdetails.UserDetailsView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

class UserDetailsPresenter(private val string: String?) : MvpPresenter<UserDetailsView>() {

    var userSubcomponent: RepositorySubcomponent? = null

    @Inject
    lateinit var repository: UserDetailsRepoScreen

    @Inject
    lateinit var router: Router

    private val bag = CompositeDisposable()
    private var mLogin: String? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        userSubcomponent = App.instance.appComponent.userSubcomponent()
        userSubcomponent?.inject(this)
        loadUser(string!!)
    }

    fun loadUser(login: String) {
        mLogin = login
        viewState.showLoading()
        repository.getUserWithReposByLogin(login).subscribeByDefault().subscribe({
            viewState.hideLoading()
            viewState.showUser(it)
        }, {
            Log.d("TAG", it.localizedMessage)
        }).disposableBy(bag)
    }

    fun onBackPressed(): Boolean {
        mLogin?.let {
            router.backTo(UserScreen(it))
        }
        return true
    }

    fun openRepoScreen(repo: ReposDto) {
        router.navigateTo(RepoScreen(repo))
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
        userSubcomponent = null
    }
}