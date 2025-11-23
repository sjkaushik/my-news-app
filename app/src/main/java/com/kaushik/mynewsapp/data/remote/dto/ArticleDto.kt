package com.kaushik.mynewsapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.kaushik.mynewsapp.domain.model.Article

data class ArticleDto(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: SourceDto,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String? =null
)

fun ArticleDto.toContentArticle(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        url = url
    )
}