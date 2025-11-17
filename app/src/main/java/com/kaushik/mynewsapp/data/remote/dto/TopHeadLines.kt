package com.kaushik.mynewsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TopHeadLines(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles: List<ArticleDto> = ArrayList(),
)
