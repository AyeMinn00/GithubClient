package com.ayeminoo.githubclient.ui.genie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ayeminoo.domain.genie.Product

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel,
    onProductInquiry: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.products.collectAsStateWithLifecycle()
    ProductsScreen(
        uiState = uiState,
        onProductInquiry = onProductInquiry,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    uiState: GenieUiState,
    onProductInquiry: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Products",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (uiState) {
                GenieUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is GenieUiState.Data -> {
                    ProductList(
                        products = uiState.data,
                        onProductInquiry = onProductInquiry
                    )
                }
            }
        }
    }
}

@Composable
fun ProductList(
    products: List<Product>,
    onProductInquiry: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(
            items = products,
            key = { item -> item.id }) { prd ->
            ProductCard(
                product = prd,
                onInquiry = onProductInquiry,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ProductScreenPreview() {
    ProductsScreen(uiState = GenieUiState.Data(
        listOf(
            Product(
                title = "Iphone 16",
                description = "Apple's Product",
                category = "Smart Phones",
                estimateCash = 60000.0,
                priceUnit = "Baht",
                availableUnit = 4171,
                tag = listOf("apple", "phones"),
                favorite = true,
                image = "",
                id = 1
            )
        )
    ), onProductInquiry = {})
}