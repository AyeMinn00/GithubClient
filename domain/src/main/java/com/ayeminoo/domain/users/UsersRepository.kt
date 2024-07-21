package com.ayeminoo.domain.users

import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.users.model.User
import com.ayeminoo.domain.users.model.UserDetail

interface UsersRepository {
    suspend fun list(since : Int): Resource<List<User>>
    suspend fun getDetail(userName : String): Resource<UserDetail>
}