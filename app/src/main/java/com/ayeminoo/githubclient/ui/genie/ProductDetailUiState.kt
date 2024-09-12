package com.ayeminoo.githubclient.ui.genie

import com.ayeminoo.domain.genie.Product

sealed interface ProductDetailUiState {
    data object Loading : ProductDetailUiState
    data class Data(val data: Product) : ProductDetailUiState
}
