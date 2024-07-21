package com.ayeminoo.githubclient.navigation.composables

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ayeminoo.githubclient.navigation.destination.ARG_USER_NAME
import com.ayeminoo.githubclient.navigation.destination.UserDetailDestination
import com.ayeminoo.githubclient.ui.users.detail.UserDetailScreen

fun NavGraphBuilder.userDetailRoute(
    navHostController: NavHostController
) {
    composable(
        route = UserDetailDestination.route,
        arguments = listOf(
            navArgument(ARG_USER_NAME) {
                type = NavType.StringType
                nullable = false
                defaultValue = ""
            }
        )
    ) { backStackEntry ->
        val userName = backStackEntry.arguments?.getString(ARG_USER_NAME)
            ?: throw IllegalArgumentException("Please provide userId")
        UserDetailScreen(
            userName = userName,
            viewModel = hiltViewModel(),
            onNavigateUp = { navHostController.navigateUp() }
        )
    }
}
