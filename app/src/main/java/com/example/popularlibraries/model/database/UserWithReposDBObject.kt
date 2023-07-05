package com.example.popularlibraries.model.database

import androidx.room.Embedded
import androidx.room.Relation
import com.example.popularlibraries.model.database.entity.UserRepoDbEntity
import com.example.popularlibraries.model.database.entity.UsersDbEntity

data class UserWithReposDBObject(
    @Embedded // распарсится на поля
    val usersDbEntity: UsersDbEntity,
    @Relation(
        parentColumn = UsersDbEntity.PRIMARY_KEY,
        entityColumn = UserRepoDbEntity.FOREIGN_USER_KEY
    )
    val repos: List<UserRepoDbEntity>
)
