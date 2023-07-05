package com.example.popularlibraries.model.repository.room

import com.example.popularlibraries.model.database.entity.UserRepoDbEntity
import com.example.popularlibraries.model.database.entity.UsersDbEntity
import io.reactivex.rxjava3.core.Completable

class CacheableImpl(
    private val usersRepo: UsersRepo,
    private val userRepositoryRepo: UserRepositoryRepo,
) : Cacheable {
    override fun insertUserList(list: List<UsersDbEntity>): Completable {
        return usersRepo.insertAll(list)
    }

    override fun insertRepoList(list: List<UserRepoDbEntity>): Completable {
        return userRepositoryRepo.insertAllRepos(list)
    }
}