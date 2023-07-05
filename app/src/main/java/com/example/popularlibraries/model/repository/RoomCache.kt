package com.example.popularlibraries.model.repository

import com.example.popularlibraries.model.database.dao.UserRepoDao
import com.example.popularlibraries.model.database.dao.UsersDao
import com.example.popularlibraries.model.database.entity.UserRepoDbEntity
import com.example.popularlibraries.model.database.entity.UsersDbEntity
import io.reactivex.rxjava3.core.Completable

class RoomCache(private val usersDao: UsersDao, private val userRepoDao: UserRepoDao) : Cacheable {
    override fun insertRepoList(list: List<UserRepoDbEntity>): Completable {
        return userRepoDao.insertAllRepos(list)
    }

    override fun insertUserList(list: List<UsersDbEntity>): Completable {
        return usersDao.insertAll(list)
    }
}