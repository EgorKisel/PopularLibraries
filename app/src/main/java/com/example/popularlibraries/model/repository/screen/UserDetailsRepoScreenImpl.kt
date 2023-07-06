package com.example.popularlibraries.model.repository.screen

import com.example.popularlibraries.common.doCompletableIf
import com.example.popularlibraries.common.mapRepos
import com.example.popularlibraries.common.mapReposToObject
import com.example.popularlibraries.common.mapToDbObject
import com.example.popularlibraries.common.mapToEntity
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.network.ReposDto
import com.example.popularlibraries.model.repository.network.GithubApiRepo
import com.example.popularlibraries.model.repository.room.Cacheable
import com.example.popularlibraries.model.repository.room.UserRepositoryRepo
import com.example.popularlibraries.model.repository.room.UsersRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class UserDetailsRepoScreenImpl(
    private val githubApiRepo: GithubApiRepo,
    private val userRepositoryRepo: UserRepositoryRepo,
    private val networkStatus: Single<Boolean>,
    private val roomCache: Cacheable
) : UserDetailsRepoScreen {

    override fun getUserWithReposByLogin(login: String): Single<GithubUser> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                getUserWithRepoApi(login)
            } else {
                getUserWithReposBD(login)
            }
        }
    }

    private fun getUserWithReposBD(login: String): Single<GithubUser> {
        return userRepositoryRepo.getUsersWithRepos(login).map { userWithRepos ->
            val user = mapToEntity(userWithRepos.usersDbEntity)
            user.repos = userWithRepos.repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                mapRepos(it)
            }
            user
        }
    }


    private fun getUserWithRepoApi(login: String): Single<GithubUser> {
        return Single.zip(getUserByLogin(login),
            getReposByLogin(login)) { user, repos ->
            repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                it
            }
            user.repos = repos
            user
        }.doCompletableIf(true) { user ->
            user.repos?.let { repos ->
                roomCache.insertRepoList(repos.map { repo ->
                    mapReposToObject(repo, user.id)
                })
            } ?: Completable.create {
                it.onError(Throwable(message = "Repos is Empty"))
            }
        }
    }

    private fun getUserByLogin(login: String): Single<GithubUser> {
        return githubApiRepo.getUser(login).map(::mapToEntity)
    }

    private fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return githubApiRepo.getRepos(login)
    }
}