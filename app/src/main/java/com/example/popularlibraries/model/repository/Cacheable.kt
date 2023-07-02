package com.example.popularlibraries.model.repository

import com.example.popularlibraries.model.database.RepoDbObject
import com.example.popularlibraries.model.database.UserDbObject
import io.reactivex.rxjava3.core.Completable

interface Cacheable {
    fun insertRepoList(list: List<RepoDbObject>): Completable
    fun insertUserList(list: List<UserDbObject>): Completable
}