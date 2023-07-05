package com.example.popularlibraries.model.repository.room

import com.example.popularlibraries.model.database.entity.UserRepoDbEntity
import com.example.popularlibraries.model.database.entity.UsersDbEntity
import io.reactivex.rxjava3.core.Completable

interface Cacheable {
    fun insertRepoList(list: List<UserRepoDbEntity>): Completable
    fun insertUserList(list: List<UsersDbEntity>): Completable
}