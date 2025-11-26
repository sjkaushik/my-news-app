package com.kaushik.mynewsapp.presentation.head_lines

import com.kaushik.mynewsapp.common.Constants.COUNTRY_CODE
import com.kaushik.mynewsapp.common.MainDispatcherRule
import com.kaushik.mynewsapp.common.Resource
import com.kaushik.mynewsapp.data.remote.dto.TopHeadLines
import com.kaushik.mynewsapp.domain.usecase.GetHeadLinesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HeadLinesViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    lateinit var headLinesViewModel: HeadLinesViewModel
    private val getHeadLinesUseCase: GetHeadLinesUseCase = mockk()


    @Test
    fun `fetchHeadlines emit loading and success`() = runTest {

        val headLines = TopHeadLines(status = "", totalResults = 0, articles = listOf())

        coEvery { getHeadLinesUseCase.invoke(COUNTRY_CODE) } returns flow {
            emit(Resource.Loading())
            emit(Resource.Success(headLines))
        }

        headLinesViewModel = HeadLinesViewModel(getHeadLinesUseCase)

        advanceUntilIdle()

        val state = headLinesViewModel.state.value

        assertFalse(state.isLoading)
        assertEquals(headLines, state.data) // success data received
        assertNull(state.error)

    }

    @Test
    fun `fetchHeadLines emit error`() = runTest {

        val errorMessage = "Network error"

        coEvery { getHeadLinesUseCase.invoke(COUNTRY_CODE) } returns flow {

            emit(Resource.Loading())
            emit(Resource.Error(errorMessage))
        }

        headLinesViewModel = HeadLinesViewModel(getHeadLinesUseCase)

        advanceUntilIdle()

        val state = headLinesViewModel.state.value

        assertFalse(state.isLoading)
        assertEquals(errorMessage, state.error)
        assertNull(state.data)

    }
}