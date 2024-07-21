package com.ayeminoo.domain.users.model

data class UserDetail(
    val name: String?,
    val company: String?,
    val location: String?,
    val followers: Int,
    val followings: Int,
    val repo: Int,
    val avatarUrl: String
)