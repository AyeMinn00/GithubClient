package com.ayeminoo.githubclient.navigation.composables

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ayeminoo.githubclient.navigation.destination.AddProductDestination
import com.ayeminoo.githubclient.navigation.destination.ProductDetailDestination
import com.ayeminoo.githubclient.navigation.destination.ProductsDestination
import com.ayeminoo.githubclient.navigation.destination.ProfileDestination
import com.ayeminoo.githubclient.ui.genie.ProductsScreen

fun NavGraphBuilder.genieHomeRoute(
    navHostController: NavHostController
) {
    composable(ProductsDestination.route) {
        ProductsScreen(
            viewModel = hiltViewModel(),
            onProductInquiry = { prd ->
                navHostController.navigate(
                    route = ProductDetailDestination.getAddress(id = prd.id)
                )
            },
            onNavigateProfile = {
                navHostController.navigate(
                    route = ProfileDestination.route
                )
            },
            onNavigateAddProduct = {
                navHostController.navigate(
                    route = AddProductDestination.route
                )
            }
        )
    }
}
