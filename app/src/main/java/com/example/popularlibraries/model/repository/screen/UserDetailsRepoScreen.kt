package com.example.popularlibraries.model.repository.screen

import com.example.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface UserDetailsRepoScreen {
    fun getUserWithReposByLogin(login: String): Single<GithubUser>
}