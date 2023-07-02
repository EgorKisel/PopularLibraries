package com.example.popularlibraries.common

import com.example.popularlibraries.model.data.GithubUser
import com.example.popularlibraries.model.data.ReposDto
import com.example.popularlibraries.model.data.UsersDto
import com.example.popularlibraries.model.database.RepoDbObject
import com.example.popularlibraries.model.database.UserDbObject

fun mapToEntity(dto: UsersDto): GithubUser {
    return GithubUser(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}

fun mapToDbObject(dto: UsersDto): UserDbObject {
    return UserDbObject(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}

fun mapToEntity(userDbObject: UserDbObject): GithubUser {
    return GithubUser(id = userDbObject.id,
        login = userDbObject.login,
        avatarUrl = userDbObject.avatarUrl,
        reposUrl = userDbObject.reposUrl)
}

fun mapRepos(repoDto: RepoDbObject): ReposDto {
    return ReposDto(
        id = repoDto.id,
        forksCount = repoDto.forks,
        name = repoDto.name,
        nodeId = repoDto.nodeId,
        createdAt = repoDto.createdAt,
        description = repoDto.description,
        language = repoDto.language,
        updatedAt = repoDto.updatedAt
    )
}

fun mapReposToObject(repoDto: ReposDto, mUserId: Int): RepoDbObject {
    return RepoDbObject(
        id = repoDto.id,
        forks = repoDto.forksCount,
        name = repoDto.name,
        userId = mUserId,
        language = repoDto.language,
        description = repoDto.description,
        createdAt = repoDto.createdAt,
        nodeId = repoDto.nodeId,
        updatedAt = repoDto.updatedAt
    )
}