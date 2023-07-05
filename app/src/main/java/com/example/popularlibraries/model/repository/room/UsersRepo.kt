package com.example.popularlibraries.model.repository.room

import com.example.popularlibraries.model.database.entity.UsersDbEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UsersRepo {
    fun insertAll(usersDbEntity: List<UsersDbEntity>): Completable
    fun queryForAllUsers(): Single<List<UsersDbEntity>>
}