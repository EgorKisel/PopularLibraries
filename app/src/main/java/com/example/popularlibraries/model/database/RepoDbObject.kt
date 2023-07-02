package com.example.popularlibraries.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Старый способ
//@Entity(
//    tableName = "repos",
//    foreignKeys = [ForeignKey(
//        entity = UserDbObject::class,
//        parentColumns = ["id"],
//        childColumns = ["userId"],
//        onDelete = ForeignKey.CASCADE
//    )]
//)

@Entity(tableName = "repos")
data class RepoDbObject(
    @PrimaryKey
    @ColumnInfo(name = PRIMARY_KEY)
    val id: Int,
    val forks: Int,
    val name: String,
    val nodeId: String,
    val description: String,
    var createdAt: String,
    val updatedAt: String,
    val language: String,
    @ColumnInfo(name = FOREIGN_USER_KEY)
    val userId: Int
) {
    companion object {
        const val PRIMARY_KEY = "id"
        const val FOREIGN_USER_KEY = "userId"
    }
}
