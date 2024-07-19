package com.ayeminoo.data.users.repository

import com.ayeminoo.data.users.remote.UsersDataSource
import com.ayeminoo.data.users.remote.model.NetworkResult
import com.ayeminoo.data.users.remote.model.toDomain
import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.users.UsersRepository
import com.ayeminoo.domain.users.model.User
import com.ayeminoo.domain.users.model.UserDetail
import javax.inject.Inject

class DefaultUsersRepository @Inject constructor(
    private val usersDataSource: UsersDataSource
) : UsersRepository {

    override suspend fun getUsers(): Resource<List<User>> {
        return when (val result = usersDataSource.list()) {
            is NetworkResult.Success -> {
                Resource.Success(data = result.data.toDomain())
            }

            is NetworkResult.Error -> {
                Resource.Error(result.exception)
            }
        }
    }

    override suspend fun getUserDetail(userName: String): Resource<UserDetail> {
        return when (val result = usersDataSource.getDetail(userName)) {
            is NetworkResult.Success -> {
                Resource.Success(data = result.data.toDomain())
            }

            is NetworkResult.Error -> {
                Resource.Error(result.exception)
            }
        }
    }

}