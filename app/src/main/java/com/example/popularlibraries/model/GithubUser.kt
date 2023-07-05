package com.example.popularlibraries.model

import android.os.Parcelable
import com.example.popularlibraries.model.network.ReposDto
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String?,
    var repos: List<ReposDto>? = null
) : Parcelable
