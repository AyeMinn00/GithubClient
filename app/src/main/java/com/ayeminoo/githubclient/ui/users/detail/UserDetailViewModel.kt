package com.ayeminoo.githubclient.ui.users.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _userDetail = MutableStateFlow<UserDetailUiState>(UserDetailUiState.Loading)
    val userDetail = _userDetail.asStateFlow()

    fun getDetail(userName: String) {
        viewModelScope.launch {
            _userDetail.update {
                UserDetailUiState.Loading
            }
            when (val result = usersRepository.getDetail(userName)) {
                is Resource.Success -> {
                    _userDetail.update {
                        UserDetailUiState.Data(result.data)
                    }
                }

                is Resource.Error -> {
                    _userDetail.update {
                        UserDetailUiState.Error
                    }
                }
            }
        }
    }
}
