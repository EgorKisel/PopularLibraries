package com.example.popularlibraries.model

import android.os.Parcelable
import com.example.popularlibraries.network.ReposDto
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String?
) : Parcelable

@Parcelize
data class GithubUserRepos(
    val user: GithubUser,
    val reposList: List<ReposDto>
) : Parcelable
