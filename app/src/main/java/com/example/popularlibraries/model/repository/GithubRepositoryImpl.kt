package com.example.popularlibraries.model.repository

import com.example.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class GithubRepositoryImpl : GithubRepository {

    private val repositories = listOf(
        GithubUser("Egor Kisel"),
        GithubUser("Rita Kisel"),
        GithubUser("Marsel Petrovich"),
        GithubUser("Anton Kisel"),
        GithubUser("Gnom Gnomych"),
    )

    override fun getUsers(): Observable<List<GithubUser>> {
        return Observable.fromIterable(listOf(repositories)).delay(1, TimeUnit.SECONDS)
    }
}