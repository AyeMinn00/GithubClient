package com.ayeminoo.data.users.remote.model

import com.ayeminoo.domain.users.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val name: String
)

fun UserDto.toDomain(): User = User(name = this.name)

fun List<UserDto>.toDomain(): List<User> = this.map { it.toDomain() }
