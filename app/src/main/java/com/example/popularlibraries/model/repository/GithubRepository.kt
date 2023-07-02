package com.example.popularlibraries.model.repository

import com.example.popularlibraries.model.data.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserWithReposByLogin(login: String): Single<GithubUser>
}