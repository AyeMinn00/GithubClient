package com.ayeminoo.data.users.remote.service

import com.ayeminoo.data.users.remote.model.UserDto
import com.ayeminoo.data.users.remote.model.UserDetailDto

interface GithubUsersApi {

    suspend fun fetch(): List<UserDto>
    suspend fun fetchDetail(userName: String): UserDetailDto

}