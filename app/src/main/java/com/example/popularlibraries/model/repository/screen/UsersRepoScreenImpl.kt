package com.example.popularlibraries.model.repository.screen

import com.example.popularlibraries.common.doCompletableIf
import com.example.popularlibraries.common.mapToDbObject
import com.example.popularlibraries.common.mapToEntity
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.repository.network.GithubApiRepo
import com.example.popularlibraries.model.repository.room.Cacheable
import com.example.popularlibraries.model.repository.room.UsersRepo
import io.reactivex.rxjava3.core.Single

class UsersRepoScreenImpl(
    private val githubApiRepo: GithubApiRepo,
    private val usersRepo: UsersRepo,
    private val networkStatus: Single<Boolean>,
    private val roomCache: Cacheable
) : UsersRepoScreen {
    override fun getUsers(): Single<List<GithubUser>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) getUsersApi(true)
            else getUsersBD()
        }
    }

    private fun getUsersApi(shouldPersist: Boolean): Single<List<GithubUser>> {
        return githubApiRepo.getAllUsers().doCompletableIf(shouldPersist) {
            roomCache.insertUserList(it.map(::mapToDbObject))
        }.map { it.map(::mapToEntity) }
    }

    private fun getUsersBD(): Single<List<GithubUser>> {
        return usersRepo.queryForAllUsers().map { it.map(::mapToEntity) }
    }
}