package com.ayeminoo.githubclient.ui.users.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserDetailScreen(
    userId: Int,
    modifier: Modifier = Modifier
) {
    Text(text = "$userId")
}
