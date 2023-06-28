package com.example.popularlibraries.model.repository

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.network.ReposDto
import io.reactivex.rxjava3.core.Single

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUsersByLogin(login: String): Single<GithubUser>
    fun getReposByLogin(login: String): Single<List<ReposDto>>
}