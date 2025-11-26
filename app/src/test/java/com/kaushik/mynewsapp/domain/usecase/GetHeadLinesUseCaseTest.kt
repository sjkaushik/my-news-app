package com.kaushik.mynewsapp.domain.usecase

import app.cash.turbine.test
import com.kaushik.mynewsapp.common.Constants.COUNTRY_CODE
import com.kaushik.mynewsapp.common.MainDispatcherRule
import com.kaushik.mynewsapp.common.Resource
import com.kaushik.mynewsapp.data.remote.dto.TopHeadLines
import com.kaushik.mynewsapp.domain.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GetHeadLinesUseCaseTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    lateinit var getHeadLinesUseCase: GetHeadLinesUseCase
    private val repository: NewsRepository = mockk()

    @Before
    fun setUp() {
        getHeadLinesUseCase = GetHeadLinesUseCase(repository)
    }

    @Test
    fun `invoke emits success`() = runTest {

        val expectedData = TopHeadLines(status = "", totalResults = 0, articles = listOf())

        coEvery { repository.getHeadLineNews(COUNTRY_CODE) } returns expectedData

        getHeadLinesUseCase(COUNTRY_CODE).test {

            val item = awaitItem()
            assertTrue(item is Resource.Success)
            assertEquals(expectedData, item.data)

            awaitComplete()
        }
    }

    @Test
    fun `invoke returns error when HttpException occurs`() = runTest {

        val exception = HttpException(Response.error<String>(400, "Bad Request".toResponseBody()))

        coEvery { repository.getHeadLineNews(any()) } throws exception

        getHeadLinesUseCase(COUNTRY_CODE).test {

            val item = awaitItem()
            assertTrue(item is Resource.Error)
            assertEquals("HTTP 400 Response.error()", item.message)

            awaitComplete()
        }
    }

    @Test
    fun `invoke returns error when IOException occurs`() = runTest {

        coEvery { repository.getHeadLineNews(any()) } throws IOException()

        getHeadLinesUseCase(COUNTRY_CODE).test {

            val item = awaitItem()
            assertTrue(item is Resource.Error)
            assertEquals(
                "Couldn't reach server. Check your internet connection.",
                item.message
            )

            awaitComplete()
        }
    }
}