package com.ayeminoo.githubclient.navigation.composables

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ayeminoo.githubclient.navigation.destination.ARG_USER_ID
import com.ayeminoo.githubclient.navigation.destination.UserDetailDestination
import com.ayeminoo.githubclient.ui.users.detail.UserDetailScreen

fun NavGraphBuilder.userDetailRoute(
    navHostController: NavHostController
) {
    composable(
        route = UserDetailDestination.route,
        arguments = listOf(
            navArgument(ARG_USER_ID) {
                type = NavType.IntType
                nullable = false
                defaultValue = 0
            }
        )
    ) { backStackEntry ->
        val userId = backStackEntry.arguments?.getInt(ARG_USER_ID)
            ?: throw IllegalArgumentException("Please provide userId")
        UserDetailScreen(userId = userId)
    }
}
