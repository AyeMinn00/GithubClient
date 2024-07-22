package com.ayeminoo.githubclient.ui.users.detail

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
class UserDetailScreenKtTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltActivity>()

    @Inject
    lateinit var usersRepository: UsersRepository

    private lateinit var viewModel: UserDetailViewModel

    @Before
    fun init() {
        hiltRule.inject()
        viewModel = UserDetailViewModel(usersRepository)
    }

    @Test
    fun userDetailScreen_VerifyUserDetailLoading() = runTest {
        composeTestRule.setContent {
            UserDetailScreen(
                uiState = viewModel.userDetail.collectAsStateWithLifecycle().value,
                onRetry = {},
                onNavigateUp = {}
            )
        }
    }

}