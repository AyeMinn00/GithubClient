package com.ayeminoo.githubclient.ui.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ayeminoo.domain.users.model.User
import com.ayeminoo.githubclient.theme.GithubClientTheme

@Composable
fun UsersScreen(
    viewModel: UsersViewModel,
    modifier: Modifier = Modifier
) {
    val users = viewModel.users.collectAsLazyPagingItems()
    UsersScreen(
        users = users,
        modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    users: LazyPagingItems<User>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier,
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Users")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding)
        ) {
            items(count = users.itemCount, key = { users[it]?.id ?: it }) {
                users[it]?.let { usr ->
                    UserCard(user = usr)
                }
            }
            users.apply {
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        item {
                            Loading(
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                    }

                    is LoadState.Error -> {
                        item {
                            InitialLoadingError(
                                msg = "Error",
                                onRetry = { retry() },
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                    }

                    is LoadState.NotLoading -> Unit
                }
                when (loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            Loading(
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    is LoadState.Error -> {
                        item {
                            LoadingError(
                                onRetry = { retry() },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    is LoadState.NotLoading -> Unit
                }
            }
        }
    }
}

@Composable
fun InitialLoadingError(
    msg: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = msg)
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedButton(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun InitialLoadingError_Preview(modifier: Modifier = Modifier) {
    GithubClientTheme {
        InitialLoadingError(
            msg = "Error",
            onRetry = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun LoadingError(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        OutlinedButton(
            onClick = onRetry,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Retry")
        }
    }
}
