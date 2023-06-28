package com.example.popularlibraries.model.repository

import com.example.popularlibraries.common.mapToEntity
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.network.ReposDto
import com.example.popularlibraries.network.UsersApi
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class GithubRepositoryImpl(private val usersApi: UsersApi) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        return usersApi.getAllUsers().map { it.map(::mapToEntity) }
            .delay(500, TimeUnit.MILLISECONDS)
    }

    override fun getUsersByLogin(login: String): Single<GithubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(500, TimeUnit.MILLISECONDS)
    }

    override fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return usersApi.getRepos(login)
    }
}