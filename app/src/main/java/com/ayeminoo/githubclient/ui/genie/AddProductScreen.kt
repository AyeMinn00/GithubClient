package com.ayeminoo.githubclient.ui.genie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ayeminoo.githubclient.theme.GithubClientTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Add Product",
                        style = MaterialTheme.typography.titleLarge
                    )
                },

                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier
                .padding(top = 8.dp)
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = "Add Title",
                onValueChange = {},
                shape = MaterialTheme.shapes.medium
            )
            OutlinedTextField(
                value = "Add Description",
                onValueChange = {},
                shape = MaterialTheme.shapes.medium
            )
            OutlinedTextField(
                value = "Add Price",
                onValueChange = {},
                shape = MaterialTheme.shapes.medium
            )
            OutlinedTextField(
                value = "Add Category",
                onValueChange = {},
                shape = MaterialTheme.shapes.medium
            )
            OutlinedTextField(
                value = "Add Available Unit",
                onValueChange = {},
                shape = MaterialTheme.shapes.medium
            )
            Button(
                onClick = { },
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Create",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AddProductPreview() {
    GithubClientTheme {
        AddProductScreen(onNavigateUp = {})
    }
}
