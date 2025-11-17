package com.kaushik.mynewsapp.domain.repository

import com.kaushik.mynewsapp.data.remote.dto.TopHeadLines

interface NewsRepository {

    suspend fun getHeadLineNews(code: String): TopHeadLines
}