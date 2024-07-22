package com.ayeminoo.githubclient.ui.users

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.compose.collectAsLazyPagingItems
import com.ayeminoo.domain.users.UsersRepository
import com.ayeminoo.githubclient.HiltActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class UsersScreenKtTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltActivity>()

    @Inject
    lateinit var usersRepository: UsersRepository

    private lateinit var viewModel: UsersViewModel

    @Before
    fun init() {
        hiltRule.inject()
        viewModel = UsersViewModel(usersRepository)
    }

    @Test
    fun usersScreen_VerifyUsersLoading() = runTest {
        composeTestRule.setContent {
            UsersScreen(
                users = viewModel.users.collectAsLazyPagingItems(),
                onUserClick = {}
            )
        }
        composeTestRule.onNodeWithText("User1") // User1 is from FakeRepository
            .assertIsDisplayed()
    }

}