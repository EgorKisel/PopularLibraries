package com.example.popularlibraries.model.repository

import com.example.popularlibraries.model.database.RepoDbObject
import com.example.popularlibraries.model.database.UserDao
import com.example.popularlibraries.model.database.UserDbObject
import io.reactivex.rxjava3.core.Completable

class RoomCache(private val userDao: UserDao): Cacheable {
    override fun insertRepoList(list: List<RepoDbObject>): Completable {
        return userDao.insertAllRepos(list)
    }

    override fun insertUserList(list: List<UserDbObject>): Completable {
        return userDao.insertAll(list)
    }
}