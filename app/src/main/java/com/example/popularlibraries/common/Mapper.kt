package com.example.popularlibraries.common

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.network.ReposDto
import com.example.popularlibraries.model.database.RepoDbObject
import com.example.popularlibraries.model.database.entity.UserRepoDbEntity
import com.example.popularlibraries.model.database.entity.UsersDbEntity
import com.example.popularlibraries.model.network.UsersDto

fun mapToEntity(dto: UsersDto): GithubUser {
    return GithubUser(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}

fun mapToDbObject(dto: UsersDto): UsersDbEntity {
    return UsersDbEntity(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}

fun mapToEntity(usersDbEntity: UsersDbEntity): GithubUser {
    return GithubUser(id = usersDbEntity.id,
        login = usersDbEntity.login,
        avatarUrl = usersDbEntity.avatarUrl,
        reposUrl = usersDbEntity.reposUrl)
}

fun mapRepos(repoDto: UserRepoDbEntity): ReposDto {
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

fun mapReposToObject(repoDto: ReposDto, mUserId: Int): UserRepoDbEntity {
    return UserRepoDbEntity(
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