package com.example.popularlibraries.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String?,
    var repos: List<ReposDto>? = null
) : Parcelable

@Parcelize
data class GithubUserRepos(
    val user: GithubUser,
    val reposList: List<ReposDto>
) : Parcelable
