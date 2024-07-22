package com.ayeminoo.githubclient.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.ayeminoo.githubclient.HiltActivity
import com.ayeminoo.githubclient.constants.TestTags.NAVIGATE_UP
import com.ayeminoo.githubclient.navigation.destination.UserDetailDestination
import com.ayeminoo.githubclient.navigation.destination.UsersDestination
import com.ayeminoo.githubclient.utils.assertCurrentRouteName
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class GithubClientNavHostKtTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            GithubClientNavHost(navController)
        }
    }

    @Test
    fun navHost_Verify_Start_Destination() {
        navController.assertCurrentRouteName(UsersDestination.route)
    }

    @Test
    fun navHost_ClickOnUser_NavigateToDetail() {
        // User1 is provided from FakeUsesRepository
        composeTestRule.onNodeWithText("User1")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("User1")
            .performClick()
        navController.assertCurrentRouteName(UserDetailDestination.route)
    }

    @Test
    fun navHost_ClickOnNavigateUpIcon_NavigateBack() {
        // User1 is provided from FakeUsesRepository
        composeTestRule.onNodeWithText("User1")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("User1")
            .performClick()
        composeTestRule.onNodeWithTag(NAVIGATE_UP)
            .performClick()
        navController.assertCurrentRouteName(UsersDestination.route)
    }

}