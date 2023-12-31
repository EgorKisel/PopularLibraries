package com.example.popularlibraries.model.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users")
    fun getAllUsers(): Single<List<UsersDto>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String): Single<UsersDto>

    @GET("/users/{login}/repos")
    fun getRepos(@Path("login") login: String): Single<List<ReposDto>>
}