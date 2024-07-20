package com.ayeminoo.githubclient.ui.users

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.users.UsersRepository
import com.ayeminoo.domain.users.model.User

class UsersPagingSource(
    private val usersRepository: UsersRepository
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.id
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val currentPage = params.key ?: 0
        return when (val result = usersRepository.getUsers(currentPage)) {
            is Resource.Success -> {
                val nextKey = if (result.data.isEmpty()) {
                    null
                } else {
                    result.data.last().id
                }
                LoadResult.Page(
                    data = result.data,
                    prevKey = null,
                    nextKey = nextKey
                )
            }

            is Resource.Error -> {
                LoadResult.Error(result.error)
            }
        }
    }
}
