package com.ayeminoo.data.users.remote

import com.ayeminoo.data.users.remote.model.NetworkResult
import com.ayeminoo.data.users.remote.model.UserDetailDto
import com.ayeminoo.data.users.remote.model.UserDto
import com.ayeminoo.data.users.remote.service.GithubUsersApi
import javax.inject.Inject

class DefaultUsersDataSource @Inject constructor(
    private val usersApi: GithubUsersApi
) : UsersDataSource {

    override suspend fun list(): NetworkResult<List<UserDto>> {
        return try {
            val response = usersApi.fetch()
            NetworkResult.Success(data = response)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    override suspend fun getDetail(userName: String): NetworkResult<UserDetailDto> {
        return try {
            val response = usersApi.fetchDetail(userName)
            NetworkResult.Success(data = response)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

}