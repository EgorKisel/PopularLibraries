package com.example.popularlibraries.view.userdetails

import com.example.popularlibraries.model.GithubUserRepos
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailsView: MvpView {
    fun showUser(user: GithubUserRepos)
    fun showLoading()
    fun hideLoading()
}