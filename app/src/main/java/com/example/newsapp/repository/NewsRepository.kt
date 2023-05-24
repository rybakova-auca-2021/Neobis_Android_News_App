package com.example.newsapp.repository
import android.widget.SearchView
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.model.Article

class NewsRepository(private val db: ArticleDatabase?) : ViewModel() {
    suspend fun getNews() = RetrofitInstance.api.getNews()
    suspend fun searchNews(query: String) = RetrofitInstance.api.searchForNews(query)
    fun getLikedNews() = db?.articleDao()?.getAllArticles()
    fun insertArticle(article : Article) = db?.articleDao()?.insertArticle(article)
    fun deleteArticle(article : Article) = db?.articleDao()?.deleteArticle(article)
}