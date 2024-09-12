package com.ayeminoo.githubclient.ui.genie

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ayeminoo.domain.genie.Product

@Composable
fun ProductDetailScreen(
    id: Int,
    viewModel: ProductDetailViewModel,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(id) {
        viewModel.getDetail(id)
    }
    val uiState by viewModel.product.collectAsStateWithLifecycle()
    ProductDetailScreen(
        uiState = uiState,
        onNavigate = onNavigate,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    uiState: ProductDetailUiState,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Product Detail",
                        style = MaterialTheme.typography.titleLarge
                    )
                },

                navigationIcon = {
                    IconButton(onClick = onNavigate) {
                        Icon(
                            Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (uiState) {
                ProductDetailUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is ProductDetailUiState.Data -> {
                    ProductDetailScreen(
                        uiState.data,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductDetailScreen(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
        ) {
            if (product.image != null) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = MaterialTheme.shapes.extraSmall
                        )
                        .clip(MaterialTheme.shapes.extraSmall)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = product.title,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Text(text = product.description, style = MaterialTheme.typography.labelSmall)
        Text(text = product.category, style = MaterialTheme.typography.labelSmall)
        Text(
            text = "Total Unit: ${product.availableUnit}",
            style = MaterialTheme.typography.labelSmall
        )
        Row {
            Button(onClick = {}, shape = MaterialTheme.shapes.extraSmall) {
                Text(text = "Apply")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {}, shape = MaterialTheme.shapes.extraSmall) {
                Text(text = "Send Message")
            }
        }
    }
}

@Preview
@Composable
private fun ProductDetailScreenPreview() {
    ProductDetailScreen(
        product = Product(
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
        ),
        modifier = Modifier.fillMaxSize()
    )
}
