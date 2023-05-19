package com.example.newsapp.repository
import android.widget.SearchView
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase

class NewsRepository(val db: ArticleDatabase?) : ViewModel() {
    suspend fun getNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getNews(countryCode, pageNumber)
    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
}