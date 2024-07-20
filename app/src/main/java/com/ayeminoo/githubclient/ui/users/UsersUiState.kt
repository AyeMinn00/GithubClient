package com.ayeminoo.githubclient.ui.users

import androidx.compose.runtime.Stable
import com.ayeminoo.domain.users.model.User

@Stable
sealed interface UsersUiState {
    data object Loading : UsersUiState
    data class Data(val users: List<User>) : UsersUiState
}
