package com.example.popularlibraries.model.repository.room

import com.example.popularlibraries.model.database.dao.UsersDao
import com.example.popularlibraries.model.database.entity.UsersDbEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class UsersRepoImpl(private val usersDao: UsersDao) : UsersRepo {
    override fun insertAll(usersDbEntity: List<UsersDbEntity>): Completable {
        return usersDao.insertAll(usersDbEntity)
    }

    override fun queryForAllUsers(): Single<List<UsersDbEntity>> {
        return usersDao.queryForAllUsers()
    }
}