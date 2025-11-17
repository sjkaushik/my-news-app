package com.kaushik.mynewsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SourceDto(
    @SerializedName("id")
    val id: Any,
    @SerializedName("name")
    val name: String
)
