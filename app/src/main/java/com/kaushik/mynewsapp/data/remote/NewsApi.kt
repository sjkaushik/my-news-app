package com.kaushik.mynewsapp.data.remote

import com.kaushik.mynewsapp.common.Constants.API_KEY
import com.kaushik.mynewsapp.data.remote.dto.TopHeadLines
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getHeadLines(@Query("country") country: String): TopHeadLines
}