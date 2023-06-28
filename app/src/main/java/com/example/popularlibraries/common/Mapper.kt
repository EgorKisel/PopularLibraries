package com.example.popularlibraries.common

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.network.UsersDto

fun mapToEntity(dto: UsersDto): GithubUser {
    return GithubUser(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}