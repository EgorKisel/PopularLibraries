package com.example.popularlibraries.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDbObject(
    @PrimaryKey
    @ColumnInfo(name = PRIMARY_KEY)
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String
) {
    companion object {
        const val PRIMARY_KEY = "id"
    }
}
