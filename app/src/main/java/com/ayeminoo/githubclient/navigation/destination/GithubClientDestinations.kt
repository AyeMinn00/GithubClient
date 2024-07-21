package com.ayeminoo.githubclient.navigation.destination

const val ARG_USER_NAME = "user_name"

object UsersDestination : Destination(path = "users")

object UserDetailDestination : Destination(
    path = "user_detail",
    arguments = listOf(ARG_USER_NAME)
) {
    fun getAddress(userName: String) = address(args = mapOf(ARG_USER_NAME to userName))
}
