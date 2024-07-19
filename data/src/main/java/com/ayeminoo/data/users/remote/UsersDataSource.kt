package com.ayeminoo.data.users.remote

import com.ayeminoo.data.users.remote.model.NetworkResult
import com.ayeminoo.data.users.remote.model.UserDetailDto
import com.ayeminoo.data.users.remote.model.UserDto

interface UsersDataSource {
    suspend fun list(): NetworkResult<List<UserDto>>
    suspend fun getDetail(userName: String): NetworkResult<UserDetailDto>
}