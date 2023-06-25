package com.example.popularlibraries.model.repository

import com.example.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Observable

interface GithubRepository {
    fun getUsers(): Observable<List<GithubUser>>
}