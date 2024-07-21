package com.ayeminoo.githubclient.ui.users.detail

import androidx.compose.runtime.Stable
import com.ayeminoo.domain.users.model.UserDetail

@Stable
sealed interface UserDetailUiState {
    data object Loading : UserDetailUiState
    data class Data(val data: UserDetail) : UserDetailUiState
    data object Error : UserDetailUiState
}
