package com.kaushik.mynewsapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaushik.mynewsapp.common.Constants.COUNTRY_CODE
import com.kaushik.mynewsapp.common.Resource
import com.kaushik.mynewsapp.domain.usecase.GetHeadLinesUseCase
import com.kaushik.mynewsapp.presentation.ui.theme.ArticleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getHeadLines: GetHeadLinesUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(ArticleState())
    val state = _state.asStateFlow()


    init {
        fetchHeadLines()
    }

    private fun fetchHeadLines(code: String = COUNTRY_CODE) {

        getHeadLines(countryCode = code).onEach { result ->

            when (result) {
                is Resource.Error -> _state.value =
                    ArticleState(error = result.message ?: " Error occured")

                is Resource.Loading -> _state.value = ArticleState(isLoading = true)
                is Resource.Success -> _state.value = ArticleState(data = result.data)
            }
        }.launchIn(viewModelScope)
    }

}