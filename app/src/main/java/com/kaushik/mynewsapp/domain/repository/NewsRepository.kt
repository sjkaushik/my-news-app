package com.kaushik.mynewsapp.domain.repository

import com.kaushik.mynewsapp.data.remote.dto.ArticleDto

interface NewsRepository {

    suspend fun getHeadLineNews(code: String): List<ArticleDto>
}