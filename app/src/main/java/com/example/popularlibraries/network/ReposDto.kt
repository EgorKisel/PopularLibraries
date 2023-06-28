package com.example.popularlibraries.network

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReposDto(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("node_id")
    val nodeId: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("created_at")
    val createdAt: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String,
    @Expose
    @SerializedName("language")
    val language: String,
    @Expose
    @SerializedName("forks_count")
    val forksCount: Int
) : Parcelable