package com.example.newsapp.model

data class NewsResponse(
    val articled: List<Article>,
    val status: String,
    val totalResults: Int
)
