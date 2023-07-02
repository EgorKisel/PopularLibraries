package com.example.popularlibraries.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(userDbObject: List<UserDbObject>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllRepos(repoDbObject: List<RepoDbObject>): Completable

    @Query("Select * from users")
    abstract fun queryForAllUsers(): Single<List<UserDbObject>>

    @Query("Select * from repos")
    abstract fun queryForAllRepos(): Single<List<RepoDbObject>>

    @Query("Select * from users where login = :login")
    abstract fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>

// Примеры запросов на удаление
//    @Query("Delete from repos")
//    abstract fun deleteAllRepos(): Completable

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insert(userDbObject: UserDbObject): Completable

//    @Query("DELETE  from repos")
//    abstract fun queryForTest(): Completable

//    @Delete
//    abstract fun delete(userDbObject: UserDbObject): Completable
}