package com.example.popularlibraries.model.database

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithReposDBObject(
    @Embedded // распарсится на поля
    val userDbObject: UserDbObject,
    @Relation(
        parentColumn = UserDbObject.PRIMARY_KEY,
        entityColumn = RepoDbObject.FOREIGN_USER_KEY
    )
    val repos: List<RepoDbObject>
)
