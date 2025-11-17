package com.kaushik.mynewsapp.presentation.head_lines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaushik.mynewsapp.common.Constants
import com.kaushik.mynewsapp.common.Resource
import com.kaushik.mynewsapp.domain.usecase.GetHeadLinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HeadLinesViewModel @Inject constructor(private val getHeadLines: GetHeadLinesUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(HeadLinesState())
    val state = _state.asStateFlow()


    init {
        fetchHeadLines()
    }

    private fun fetchHeadLines(code: String = Constants.COUNTRY_CODE) {

        getHeadLines(countryCode = code).onEach { result ->

            when (result) {
                is Resource.Error -> _state.value =
                    HeadLinesState(error = result.message ?: " Error occured")

                is Resource.Loading -> _state.value = HeadLinesState(isLoading = true)
                is Resource.Success -> _state.value = HeadLinesState(data = result.data)
            }
        }.launchIn(viewModelScope)
    }

}