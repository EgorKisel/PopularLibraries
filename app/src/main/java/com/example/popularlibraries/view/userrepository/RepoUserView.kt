package com.example.popularlibraries.view.userrepository

import com.example.popularlibraries.model.network.ReposDto
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoUserView : MvpView {
    fun showRepo(repo: ReposDto)
}