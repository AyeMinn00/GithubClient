package com.ayeminoo.githubclient.data

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.users.UsersRepository
import com.ayeminoo.domain.users.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UsersPagingSourceTest {

    private val userRepo: UsersRepository = mockk()
    private lateinit var pagingSource: UsersPagingSource

    @Before
    fun setUp() {
        pagingSource = UsersPagingSource(usersRepository = userRepo)
    }

    @Test
    fun loadUsers_PagingRefresh() = runTest {
        val users = (1..30).map {
            User(
                it,
                "User${it}",
                ""
            )
        }
        coEvery { userRepo.list(any()) }
            .returns(Resource.Success(data = users))
        val pager = TestPager(
            PagingConfig(30),
            pagingSource
        )
        val result = pager.refresh() as PagingSource.LoadResult.Page
        assertEquals(result.data, users)
    }

    @Test
    fun loadUsers_PagingRefresh_NextPageError() = runTest {
        val users = (1..30).map {
            User(it, "User${it}", "")
        }
        coEvery { userRepo.list(any()) }
            .returns(Resource.Success(data = users))
        val pager = TestPager(
            PagingConfig(30),
            pagingSource
        )
        pager.refresh()
        val errMsg = "Simulated Error"
        coEvery { userRepo.list(any()) }
            .returns(Resource.Error(Exception(errMsg)))
        val result = pager.append()
        assertEquals(1, pager.getPages().size)
        val expected = PagingSource.LoadResult.Error<Int, User>(Exception(errMsg))
        assertEquals(
            expected.throwable.message,
            (result as PagingSource.LoadResult.Error).throwable.message
        )
    }

    @Test
    fun loadUsers_PagingRefresh_NextPageError_RetrySuccess() = runTest {
        val users1 = (1..30).map {
            User(it, "User${it}", "")
        }
        val users2 = (31..60).map {
            User(it, "User${it}", "")
        }
        coEvery { userRepo.list(any()) }
            .returns(Resource.Success(data = users1))
        val pager = TestPager(
            PagingConfig(30),
            pagingSource
        )
        pager.refresh()
        coEvery { userRepo.list(any()) }
            .returns(Resource.Error(Exception()))
        pager.append()
        assertEquals(1, pager.getPages().size)
        coEvery { userRepo.list(any()) }
            .returns(Resource.Success(data = users2))
        pager.append()
        assertEquals(2, pager.getPages().size)
        assertEquals(users1 , pager.getPages()[0].data)
        assertEquals(users2 , pager.getPages()[1].data)
    }

}