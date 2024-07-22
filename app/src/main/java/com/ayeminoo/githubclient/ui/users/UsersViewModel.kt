package com.ayeminoo.githubclient.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ayeminoo.domain.users.UsersRepository
import com.ayeminoo.githubclient.data.UsersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val USERS_PAGE_SIZE = 30

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    val users = Pager(
        config = PagingConfig(
            pageSize = USERS_PAGE_SIZE,
        ),
        pagingSourceFactory = { UsersPagingSource(usersRepository) }
    ).flow.cachedIn(viewModelScope)
}
