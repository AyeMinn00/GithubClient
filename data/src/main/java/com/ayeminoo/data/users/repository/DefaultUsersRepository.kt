package com.ayeminoo.data.users.repository

import com.ayeminoo.common.di.IoDispatcher
import com.ayeminoo.data.users.remote.UsersDataSource
import com.ayeminoo.data.users.remote.model.NetworkResult
import com.ayeminoo.data.users.remote.model.toDomain
import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.users.UsersRepository
import com.ayeminoo.domain.users.model.User
import com.ayeminoo.domain.users.model.UserDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultUsersRepository
@Inject constructor(
    private val
    usersDataSource: UsersDataSource,
    @IoDispatcher private
    val dispatcher: CoroutineDispatcher
) : UsersRepository {

    override suspend fun list(since: Int): Resource<List<User>> = withContext(dispatcher) {
        when (val result = usersDataSource.list(since)) {
            is NetworkResult.Success -> {
                Resource.Success(data = result.data.toDomain())
            }

            is NetworkResult.Error -> {
                Resource.Error(result.exception)
            }
        }
    }

    override suspend fun getDetail(userName: String): Resource<UserDetail> =
        withContext(dispatcher) {
            when (val result = usersDataSource.getDetail(userName)) {
                is NetworkResult.Success -> {
                    Resource.Success(data = result.data.toDomain())
                }

                is NetworkResult.Error -> {
                    Resource.Error(result.exception)
                }
            }
        }

}