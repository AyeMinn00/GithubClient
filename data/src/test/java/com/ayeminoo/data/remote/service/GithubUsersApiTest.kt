package com.ayeminoo.data.remote.service

import com.ayeminoo.data.users.remote.service.GithubUsersApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

class GithubUsersApiTest {

    private lateinit var apiService: GithubUsersApi
    private lateinit var mockWebServer: MockWebServer

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create()
    }

    @After
    fun clear() {
        mockWebServer.shutdown()
    }

    @Test
    fun successUsersNetworkCall() = runTest {
        enqueueResponse("users/users.json")
        val response = apiService.fetch(since = 0)
        val request = mockWebServer.takeRequest()
        assertEquals(request.path, "/users?since=0")
        assertNotNull(response)
        assertEquals("GET", request.method)
        assertEquals(3, response.size)
        assertEquals(1, response[0].id)
        assertEquals("mojombo", response[0].name)
        assertEquals("https://avatars.githubusercontent.com/u/1?v=4", response[0].avatarUrl)
    }

    @Test(expected = Exception::class)
    fun usersMalformedJson() = runTest {
        enqueueResponse("malformed.json")
        apiService.fetch(0)
    }


    @Test
    fun successUserDetailNetworkCall() = runTest {
        enqueueResponse("detail/detail.json")
        val userName = "mojombo"
        val response = apiService.fetchDetail(userName = userName)
        val request = mockWebServer.takeRequest()
        assertEquals(request.path, "/users/${userName}")
        assertNotNull(response)
        assertEquals("GET", request.method)
        assertEquals("John", response.name)
        assertEquals("ABC Company", response.company)
        assertEquals("San Francisco", response.location)
        assertEquals(12, response.repo)
        assertEquals(123, response.followers)
        assertEquals(1, response.followings)
        assertEquals("https://avatars.githubusercontent.com/u/1?v=4", response.avatarUrl)
    }

    @Test(expected = Exception::class)
    fun userDetailMalformedJson() = runTest {
        enqueueResponse("malformed.json")
        apiService.fetchDetail("")
    }

    private fun enqueueResponse(path: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-responses/${path}")
        val mockResponse = MockResponse()
        val source = inputStream.source().buffer()
        mockWebServer.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8))
        )
    }

}