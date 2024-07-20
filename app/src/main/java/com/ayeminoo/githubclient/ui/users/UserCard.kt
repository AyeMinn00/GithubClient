package com.ayeminoo.githubclient.ui.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ayeminoo.domain.users.model.User
import com.ayeminoo.githubclient.theme.GithubClientTheme

@Composable
fun UserCard(
    user: User,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = user.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = user.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 8.dp
                )
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserCard_Preview(modifier: Modifier = Modifier) {
    GithubClientTheme {
        UserCard(
            user = User(
                id = 6973,
                name = "Avis Mays",
                avatarUrl = ""
            )
        )
    }
}
