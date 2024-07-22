package com.ayeminoo.githubclient.ui.users.detail

import app.cash.turbine.test
import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.users.UsersRepository
import com.ayeminoo.domain.users.model.UserDetail
import com.ayeminoo.githubclient.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserDetailViewModelTest {

    @get:Rule
    val dispatcherRule = MainCoroutineRule()

    private lateinit var userRepo: UsersRepository

    private lateinit var viewModel: UserDetailViewModel

    @Before
    fun setUp() {
        userRepo = mockk()
        viewModel = UserDetailViewModel(userRepo)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getDetail() = runTest {
        val avatarUrl = "https://search.yahoo.com"
        val result = Resource.Success(
            data = UserDetail(
                name = "xxx",
                company = "abc",
                location = "lc",
                followers = 1,
                followings = 1,
                repo = 11,
                avatarUrl = avatarUrl
            )
        )
        coEvery { userRepo.getDetail(any()) }.returns(result)
        viewModel.userDetail.test {
            assert(awaitItem() is UserDetailUiState.Loading)
            viewModel.getDetail("x")
            assert(awaitItem() is UserDetailUiState.Data)
        }
    }

    @Test
    fun getDetailError() = runTest {
        coEvery { userRepo.getDetail(any()) }.returns(Resource.Error(Exception()))
        viewModel.userDetail.test {
            assert(awaitItem() is UserDetailUiState.Loading)
            viewModel.getDetail("x")
            assert(awaitItem() is UserDetailUiState.Error)
        }
    }
}