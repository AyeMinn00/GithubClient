package com.ayeminoo.githubclient.ui.users

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.ayeminoo.githubclient.HiltActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class UsersScreenKtTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltActivity>()

    @Test
    fun usersScreen_InitialLoading(){
        composeTestRule.setContent {
        }
    }


}