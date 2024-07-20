package com.ayeminoo.data.users.remote.model

import com.ayeminoo.domain.users.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("login")
    val name: String,
    @SerialName("id")
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String,
)

fun UserDto.toDomain(): User = User(
    name = this.name,
    id = this.id,
    avatarUrl = this.avatarUrl
)

fun List<UserDto>.toDomain(): List<User> = this.map { it.toDomain() }
