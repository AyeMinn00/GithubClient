package com.ayeminoo.githubclient.navigation.composables

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ayeminoo.githubclient.navigation.destination.ARG_PRODUCT_ID
import com.ayeminoo.githubclient.navigation.destination.ProductDetailDestination

fun NavGraphBuilder.productDetailRoute(
    navHostController: NavHostController
) {
    composable(
        route = ProductDetailDestination.route,
        arguments = listOf(
            navArgument(ARG_PRODUCT_ID) {
                type = NavType.IntType
                nullable = false
            }
        )
    ) { backStackEntry ->
        val prdId = backStackEntry.arguments?.getInt(ARG_PRODUCT_ID)
            ?: throw IllegalArgumentException("Please provide product id")

    }
}