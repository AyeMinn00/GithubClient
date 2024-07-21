package com.ayeminoo.githubclient.navigation.composables

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ayeminoo.githubclient.navigation.destination.UserDetailDestination
import com.ayeminoo.githubclient.navigation.destination.UsersDestination
import com.ayeminoo.githubclient.ui.users.UsersScreen

fun NavGraphBuilder.usersRoute(
    navHostController: NavHostController
) {
    composable(UsersDestination.route) {
        UsersScreen(
            viewModel = hiltViewModel(),
            onUserClick = { usr ->
                navHostController.navigate(
                    route = UserDetailDestination.getAddress(userId = usr.id)
                )
            }
        )
    }
}
