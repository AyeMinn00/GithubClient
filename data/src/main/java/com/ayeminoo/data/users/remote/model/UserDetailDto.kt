package com.ayeminoo.data.users.remote.model

import com.ayeminoo.domain.users.model.UserDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailDto(
    @SerialName("name")
    val name: String? = null,
    @SerialName("company")
    val company: String? = null,
    @SerialName("location")
    val location: String? = null,
    @SerialName("followers")
    val followers: Int,
    @SerialName("following")
    val followings: Int,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("public_repos")
    val repo: Int
)

fun UserDetailDto.toDomain(): UserDetail = UserDetail(
    name = this.name,
    company = this.company,
    location = this.location,
    followers = this.followers,
    followings = this.followings,
    repo = this.repo,
    avatarUrl = this.avatarUrl
)