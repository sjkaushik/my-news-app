package com.kaushik.mynewsapp.presentation.ui.theme

import com.kaushik.mynewsapp.data.remote.dto.ArticleDto

data class ArticleState(
    val isLoading: Boolean = false,
    val data : List<ArticleDto>? =null,
    val error : String? = null
)
