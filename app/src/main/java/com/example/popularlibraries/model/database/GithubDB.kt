package com.example.popularlibraries.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserDbObject::class, RepoDbObject::class], version = 1, exportSchema = false)
abstract class GithubDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        fun create(context: Context): GithubDB {
            // fallbackToDestructiveMigration() это лучше не использовать, лучше миграции
            return Room.databaseBuilder(context, GithubDB::class.java, "github.db").build()
        }
    }
}