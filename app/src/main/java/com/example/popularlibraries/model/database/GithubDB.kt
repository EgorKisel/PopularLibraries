package com.example.popularlibraries.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularlibraries.model.database.dao.UserRepoDao
import com.example.popularlibraries.model.database.dao.UsersDao
import com.example.popularlibraries.model.database.entity.UserRepoDbEntity
import com.example.popularlibraries.model.database.entity.UsersDbEntity

@Database(
    entities = [UsersDbEntity::class, UserRepoDbEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDB : RoomDatabase() {

    abstract fun usersDao(): UsersDao
    abstract fun userRepoDao(): UserRepoDao

    companion object {
        fun create(context: Context): GithubDB {
            // fallbackToDestructiveMigration() это лучше не использовать, лучше миграции
            return Room.databaseBuilder(context, GithubDB::class.java, "github.db").build()
        }
    }
}