package com.ayeminoo.githubclient.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.users.UsersRepository
import com.ayeminoo.domain.users.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
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
