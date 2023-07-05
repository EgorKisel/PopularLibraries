package com.example.popularlibraries.model.repository.network

import com.example.popularlibraries.model.network.ReposDto
import com.example.popularlibraries.model.network.UsersDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Path

interface GithubApiRepo {

    fun getAllUsers(): Single<List<UsersDto>>

    fun getUser(@Path("login") login: String): Single<UsersDto>

    fun getRepos(@Path("login") login: String): Single<List<ReposDto>>
}