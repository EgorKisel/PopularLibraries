package com.example.popularlibraries.model.repository.network

import com.example.popularlibraries.model.network.GithubApi
import com.example.popularlibraries.model.network.ReposDto
import com.example.popularlibraries.model.network.UsersDto
import io.reactivex.rxjava3.core.Single

class GithubApiRepoImpl(private val githubApi: GithubApi) : GithubApiRepo {
    override fun getAllUsers(): Single<List<UsersDto>> {
        return githubApi.getAllUsers()
    }

    override fun getUser(login: String): Single<UsersDto> {
        return githubApi.getUser(login)
    }

    override fun getRepos(login: String): Single<List<ReposDto>> {
        return githubApi.getRepos(login)
    }
}