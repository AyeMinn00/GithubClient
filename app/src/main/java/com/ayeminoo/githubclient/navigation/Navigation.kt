package com.ayeminoo.githubclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ayeminoo.githubclient.navigation.composables.userDetailRoute
import com.ayeminoo.githubclient.navigation.composables.usersRoute
import com.ayeminoo.githubclient.navigation.destination.UsersDestination

@Composable
fun GithubClientNavHost(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = UsersDestination.route
    ) {
        usersRoute(navHostController)
        userDetailRoute(navHostController)
    }
}
