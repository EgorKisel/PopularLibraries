package com.example.popularlibraries.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.popularlibraries.model.database.entity.UsersDbEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(usersDbEntity: List<UsersDbEntity>): Completable

    @Query("Select * from users")
    abstract fun queryForAllUsers(): Single<List<UsersDbEntity>>
}