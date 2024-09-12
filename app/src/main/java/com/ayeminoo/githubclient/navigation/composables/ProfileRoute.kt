package com.ayeminoo.githubclient.navigation.composables

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ayeminoo.githubclient.navigation.destination.ProfileDestination
import com.ayeminoo.githubclient.ui.genie.ProfileScreen

fun NavGraphBuilder.profileRoute(
    navHostController: NavHostController
) {
    composable(ProfileDestination.route) {
        ProfileScreen(onNavigateUp = {
            navHostController.navigateUp()
        })
    }
}
