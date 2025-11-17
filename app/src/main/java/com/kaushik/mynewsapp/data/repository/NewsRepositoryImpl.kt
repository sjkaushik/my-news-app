package com.kaushik.mynewsapp.data.repository

import com.kaushik.mynewsapp.data.remote.NewsApi
import com.kaushik.mynewsapp.data.remote.dto.TopHeadLines
import com.kaushik.mynewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val api: NewsApi) : NewsRepository {

    override suspend fun getHeadLineNews(code: String): TopHeadLines {
        return api.getHeadLines(code)
    }
}