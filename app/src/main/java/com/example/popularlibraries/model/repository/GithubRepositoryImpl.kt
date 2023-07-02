package com.example.popularlibraries.model.repository

import com.example.popularlibraries.common.doCompletableIf
import com.example.popularlibraries.common.mapRepos
import com.example.popularlibraries.common.mapReposToObject
import com.example.popularlibraries.common.mapToDbObject
import com.example.popularlibraries.common.mapToEntity
import com.example.popularlibraries.model.data.GithubUser
import com.example.popularlibraries.model.data.ReposDto
import com.example.popularlibraries.model.database.UserDao
import com.example.popularlibraries.model.network.UsersApi
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class GithubRepositoryImpl(
    private val usersApi: UsersApi,
    private val userDao: UserDao,
    private val networkStatus: Single<Boolean>,
    private val roomCache: Cacheable = RoomCache(userDao)
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) getUsersApi(true)
            else getUsersBD()
        }
    }

    override fun getUserWithReposByLogin(login: String): Single<GithubUser> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) getUserWithRepoApi(login)
            else getUserWithRepoBD(login)
        }
    }

    private fun getUsersApi(shouldPersist: Boolean): Single<List<GithubUser>> {
        return usersApi.getAllUsers().doCompletableIf(shouldPersist) {
            roomCache.insertUserList(it.map(::mapToDbObject))
        }.map { it.map(::mapToEntity) }
    }

    private fun getUsersBD(): Single<List<GithubUser>> {
        return userDao.queryForAllUsers().map { it.map(::mapToEntity) }
    }

    private fun getUserWithRepoBD(login: String): Single<GithubUser> {
        return userDao.getUsersWithRepos(login).map { userWithRepos ->
            val user = mapToEntity(userWithRepos.userDbObject)
            user.repos = userWithRepos.repos.map {
                it.createdAt = it.createdAt.substring(0, 10).toString()
                mapRepos(it)
            }
            user
        }
    }

    private fun getUserWithRepoApi(login: String): Single<GithubUser> {
        return Single.zip(
            getUsersByLogin(login),
            getReposByLogin(login)
        ) { user, repos ->
            repos.map {
                it.createdAt = it.createdAt.substring(0, 10).toString()
                it
            }
            user.repos = repos
            user
        }.doCompletableIf(true) {user ->
            user.repos?.let { repos ->
                roomCache.insertRepoList(repos.map { repo ->
                    mapReposToObject(repo, user.id)
                })
            } ?: Completable.create {
                it.onError(Throwable(message = "Repos is Empty"))
            }
        }
    }

    private fun getUsersByLogin(login: String): Single<GithubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(500, TimeUnit.MILLISECONDS)
    }

    private fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return usersApi.getRepos(login)
    }
}