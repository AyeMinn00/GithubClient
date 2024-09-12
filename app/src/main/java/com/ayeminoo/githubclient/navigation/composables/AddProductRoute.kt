package com.ayeminoo.githubclient.navigation.composables

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ayeminoo.githubclient.navigation.destination.AddProductDestination
import com.ayeminoo.githubclient.ui.genie.AddProductScreen

fun NavGraphBuilder.addProductRoute(
    navHostController: NavHostController
) {
    composable(AddProductDestination.route) {
        AddProductScreen(onNavigateUp = {
            navHostController.navigateUp()
        })
    }
}
