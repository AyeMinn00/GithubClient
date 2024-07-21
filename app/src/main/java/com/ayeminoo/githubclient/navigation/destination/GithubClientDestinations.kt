package com.ayeminoo.githubclient.navigation.destination

const val ARG_USER_ID = "user_id"

object UsersDestination : Destination(path = "users")

object UserDetailDestination : Destination(
    path = "user_detail",
    arguments = listOf(ARG_USER_ID)
) {
    fun getAddress(userId: Int) = address(args = mapOf(ARG_USER_ID to userId))
}
