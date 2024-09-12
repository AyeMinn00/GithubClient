package com.ayeminoo.githubclient.navigation.destination

const val ARG_USER_NAME = "user_name"
const val ARG_PRODUCT_ID = "product_id"

object UsersDestination : Destination(path = "users")

object UserDetailDestination : Destination(
    path = "user_detail",
    arguments = listOf(ARG_USER_NAME)
) {
    fun getAddress(userName: String) = address(args = mapOf(ARG_USER_NAME to userName))
}

object ProductsDestination : Destination(path = "products")

object ProductDetailDestination : Destination(
    path = "product_detail",
    arguments = listOf(ARG_PRODUCT_ID)
) {
    fun getAddress(id: Int) = address(args = mapOf(ARG_PRODUCT_ID to id))
}