package com.ayeminoo.githubclient.ui.genie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ayeminoo.domain.genie.Product
import com.ayeminoo.githubclient.theme.GithubClientTheme

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel,
    onProductInquiry: (Product) -> Unit,
    onNavigateProfile: () -> Unit,
    onNavigateAddProduct: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.products.collectAsStateWithLifecycle()
    ProductsScreen(
        uiState = uiState,
        onProductInquiry = onProductInquiry,
        onNavigateProfile = onNavigateProfile,
        onNavigateAddProduct = onNavigateAddProduct,
        modifier = modifier
    )
}

@Composable
fun ProductsScreen(
    uiState: GenieUiState,
    onProductInquiry: (Product) -> Unit,
    onNavigateProfile: () -> Unit,
    onNavigateAddProduct: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    Scaffold(
        modifier,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    placeholder = {
                        Text(
                            "Search",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                        )
                    },
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .height(50.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            shape = MaterialTheme.shapes.extraLarge
                        ),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(lineHeight = 14.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onNavigateProfile) {
                    Icon(Icons.Outlined.Settings, contentDescription = "")
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateAddProduct,
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
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
            key = { item -> item.id }
        ) { prd ->
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
    GithubClientTheme {
        ProductsScreen(
            uiState = GenieUiState.Data(
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
            ),
            onProductInquiry = {},
            onNavigateProfile = {},
            onNavigateAddProduct = {}
        )
    }
}
