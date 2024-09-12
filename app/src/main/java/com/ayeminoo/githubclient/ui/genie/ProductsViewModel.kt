package com.ayeminoo.githubclient.ui.genie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.genie.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<GenieUiState>(GenieUiState.Loading)
    val products = _products.asStateFlow()

    init {
        viewModelScope.launch {
            when (val data = repo.list()) {
                is Resource.Success -> {
                    _products.update { GenieUiState.Data(data = data.data) }
                }

                is Resource.Error -> Unit
            }
        }
    }

}