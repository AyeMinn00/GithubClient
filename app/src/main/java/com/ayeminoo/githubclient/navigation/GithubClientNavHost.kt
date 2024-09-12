package com.ayeminoo.githubclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ayeminoo.githubclient.navigation.composables.addProductRoute
import com.ayeminoo.githubclient.navigation.composables.genieHomeRoute
import com.ayeminoo.githubclient.navigation.composables.productDetailRoute
import com.ayeminoo.githubclient.navigation.composables.profileRoute
import com.ayeminoo.githubclient.navigation.composables.userDetailRoute
import com.ayeminoo.githubclient.navigation.composables.usersRoute
import com.ayeminoo.githubclient.navigation.destination.ProductsDestination

@Composable
fun GithubClientNavHost(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = ProductsDestination.route
    ) {
        usersRoute(navHostController)
        userDetailRoute(navHostController)
        genieHomeRoute(navHostController)
        productDetailRoute(navHostController)
        addProductRoute(navHostController)
        profileRoute(navHostController)
    }
}
