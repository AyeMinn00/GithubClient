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
class ProductDetailViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {

    private val _product = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val product = _product.asStateFlow()

    fun getDetail(id: Int) {
        viewModelScope.launch {
            when (val result = repo.detail(id)) {
                is Resource.Success -> {
                    _product.update {
                        ProductDetailUiState.Data(
                            data = result.data
                        )
                    }
                }

                is Resource.Error -> Unit
            }
        }
    }
}
