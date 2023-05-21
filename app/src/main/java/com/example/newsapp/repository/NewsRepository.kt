package com.example.newsapp.repository
import android.widget.SearchView
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.model.Article

class NewsRepository(val db: ArticleDatabase?) : ViewModel() {
    suspend fun getNews() =
        RetrofitInstance.api.getNews()
    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun insertArticle(article : Article) {
        db?.articleDao()?.insertArticle(article)
    }

    fun getLikedNews() = db?.articleDao()?.getAllArticles()

    suspend fun deleteArticle(article : Article) {
        db?.articleDao()?.deleteArticle(article)
    }
}