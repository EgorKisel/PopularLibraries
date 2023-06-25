package com.example.popularlibraries.view.users

import com.example.popularlibraries.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun initList(list: List<GithubUser>)
    fun showLoading()
    fun hideLoading()
    fun errorGetUser(message: String?)
}

interface ItemClickListener {
    fun onUserClick(user: GithubUser)
}