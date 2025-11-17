package com.kaushik.mynewsapp.data.repository

import com.kaushik.mynewsapp.data.remote.NewsApi
import com.kaushik.mynewsapp.data.remote.dto.ArticleDto
import com.kaushik.mynewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val api: NewsApi) : NewsRepository {

    override suspend fun getHeadLineNews(code: String): List<ArticleDto> {
        return api.getHeadLines(code)
    }
}