package com.ayeminoo.domain.users

import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.users.model.User
import com.ayeminoo.domain.users.model.UserDetail

interface UsersRepository {
    suspend fun getUsers(): Resource<List<User>>
    suspend fun getUserDetail(userName: String): Resource<UserDetail>
}