package com.ayeminoo.githubclient.ui.users

import androidx.compose.runtime.Stable
import com.ayeminoo.githubclient.data.remote.model.UserDto

@Stable
sealed interface UsersUiState {
    data object Loading : UsersUiState
    data class Data(val users: List<UserDto>) : UsersUiState
}
