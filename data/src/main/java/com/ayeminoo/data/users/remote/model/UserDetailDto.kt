package com.ayeminoo.data.users.remote.model

import com.ayeminoo.domain.users.model.UserDetail
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailDto(
    val name: String
)

fun UserDetailDto.toDomain(): UserDetail = UserDetail(name = this.name)