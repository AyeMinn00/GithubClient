package com.ayeminoo.githubclient.ui.users.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ayeminoo.domain.users.model.UserDetail
import com.ayeminoo.githubclient.R
import com.ayeminoo.githubclient.theme.GithubClientTheme
import com.ayeminoo.githubclient.ui.users.Loading
import com.ayeminoo.githubclient.ui.users.LoadingError

@Composable
fun UserDetailScreen(
    userName: String,
    viewModel: UserDetailViewModel,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(userName) {
        viewModel.getDetail(userName)
    }
    val uiState by viewModel.userDetail.collectAsStateWithLifecycle()
    UserDetailScreen(
        uiState = uiState,
        onRetry = { viewModel.getDetail(userName) },
        onNavigateUp = onNavigateUp,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    uiState: UserDetailUiState,
    onRetry: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier, topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            title = {
                Text("User Detail", style = MaterialTheme.typography.titleLarge)
            },
            navigationIcon = {
                IconButton(
                    onClick = onNavigateUp,
                    modifier = Modifier.testTag("navigateBack")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
        )
    }) { innerPadding ->

        when (uiState) {
            is UserDetailUiState.Loading -> {
                Loading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            is UserDetailUiState.Error -> {
                LoadingError(
                    onRetry = onRetry,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            is UserDetailUiState.Data -> {
                UserDetail(
                    userDetail = uiState.data,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun UserDetail(
    userDetail: UserDetail,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImage(
                model = userDetail.avatarUrl,
                contentDescription = userDetail.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = userDetail.name ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (userDetail.company != null && userDetail.company!!.isNotBlank()) {
            InfoRow(
                drawableResource = R.drawable.outline_company_24,
                description = userDetail.company ?: "",
                content = {
                    Text(
                        text = userDetail.company ?: "",
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .fillMaxWidth()
            )
        }
        if (userDetail.location != null && userDetail.location!!.isNotBlank()) {
            InfoRow(
                drawableResource = R.drawable.outline_location_24,
                description = userDetail.location ?: "",
                content = {
                    Text(
                        text = userDetail.location ?: "",
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .fillMaxWidth()
            )
        }
        InfoRow(
            drawableResource = R.drawable.outline_person_24,
            description = "follower",
            content = {
                FollowerFollowingRow(
                    follower = userDetail.followers,
                    following = userDetail.followings
                )
            },
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 4.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun InfoRow(
    drawableResource: Int,
    description: String,
    content: @Composable (() -> Unit),
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = drawableResource),
            contentDescription = description,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        content()
    }
}

@Composable
fun FollowerFollowingRow(
    follower: Int,
    following: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$follower", style = MaterialTheme.typography.bodyLarge)
        Text(text = "followers", style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = Icons.Filled.Circle,
            contentDescription = "Back",
            modifier = Modifier.size(4.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$following", style = MaterialTheme.typography.bodyLarge)
        Text(text = "following", style = MaterialTheme.typography.labelMedium)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserDetail_Preview() {
    GithubClientTheme {
        UserDetailScreen(
            uiState = UserDetailUiState.Data(
                data = UserDetail(
                    name = "Tonya Rivas",
                    company = "SevenPeaks Software",
                    location = "Tokyo, Ota-ku",
                    followers = 825,
                    followings = 13,
                    repo = 3760,
                    avatarUrl = "http://www.bing.com/search?q=signiferumque"
                )
            ),
            onRetry = {},
            onNavigateUp = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
