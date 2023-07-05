package com.example.popularlibraries.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.popularlibraries.model.database.UserWithReposDBObject
import com.example.popularlibraries.model.database.entity.UserRepoDbEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class UserRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllRepos(userRepoDbEntity: List<UserRepoDbEntity>): Completable

    @Query("Select * from repos")
    abstract fun queryForAllRepos(): Single<List<UserRepoDbEntity>>

    @Transaction
    @Query("Select * from users where login = :login")
    abstract fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>
}