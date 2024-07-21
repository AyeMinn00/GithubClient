package com.ayeminoo.data.users.remote.service

import com.ayeminoo.data.users.remote.model.UserDto
import com.ayeminoo.data.users.remote.model.UserDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUsersApi {

    @GET("/users")
    suspend fun fetch(
        @Query("since") since: Int
    ): List<UserDto>

    @GET("/users/{userName}")
    suspend fun fetchDetail(@Path("userName") userName: String): UserDetailDto

}