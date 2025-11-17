package com.kaushik.mynewsapp.presentation.head_lines

import com.kaushik.mynewsapp.data.remote.dto.TopHeadLines

data class HeadLinesState(
    val isLoading: Boolean = false,
    val data: TopHeadLines? = null,
    val error: String? = null
)