package com.ayeminoo.githubclient.ui.genie

import com.ayeminoo.domain.genie.Product

sealed interface GenieUiState {
    data object Loading : GenieUiState
    data class Data(val data: List<Product>) : GenieUiState
}
