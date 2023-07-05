package com.example.popularlibraries.model.repository.room

import com.example.popularlibraries.model.database.UserWithReposDBObject
import com.example.popularlibraries.model.database.dao.UserRepoDao
import com.example.popularlibraries.model.database.entity.UserRepoDbEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class UserRepositoryRepoImpl(private val userRepoDao: UserRepoDao) : UserRepositoryRepo {
    override fun insertAllRepos(userRepoDbEntity: List<UserRepoDbEntity>): Completable {
        return userRepoDao.insertAllRepos(userRepoDbEntity)
    }

    override fun queryForAllRepos(): Single<List<UserRepoDbEntity>> {
        return userRepoDao.queryForAllRepos()
    }

    override fun getUsersWithRepos(login: String): Single<UserWithReposDBObject> {
        return userRepoDao.getUsersWithRepos(login)
    }
}