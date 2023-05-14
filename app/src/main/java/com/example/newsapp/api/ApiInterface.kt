package com.example.newsapp.api

import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("query") query: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String
    ): Call<Article>
}

