package com.ayeminoo.githubclient.fake

import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.users.UsersRepository
import com.ayeminoo.domain.users.model.User
import com.ayeminoo.domain.users.model.UserDetail

class FakeUsersRepository : UsersRepository {

    override suspend fun list(since: Int): Resource<List<User>> {
        val users = (1..20).map {
            User(
                id = since + it,
                name = "User${since + it}",
                avatarUrl = "http://google.com"
            )
        }
        return Resource.Success(users)
    }

    override suspend fun getDetail(userName: String): Resource<UserDetail> {
        return Resource.Success(data = UserDetail(
            name = null,
            company = null,
            location = null,
            followers = 3376,
            followings = 4343,
            repo = 7865,
            avatarUrl = "https://www.google.com/#q=lacus"
        ))
    }
}