package com.example.popularlibraries.model.repository.room

import com.example.popularlibraries.model.database.UserWithReposDBObject
import com.example.popularlibraries.model.database.entity.UserRepoDbEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserRepositoryRepo {
    fun insertAllRepos(userRepoDbEntity: List<UserRepoDbEntity>): Completable
    fun queryForAllRepos(): Single<List<UserRepoDbEntity>>
    fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>
}