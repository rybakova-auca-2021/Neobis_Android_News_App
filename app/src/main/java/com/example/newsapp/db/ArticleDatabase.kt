package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@androidx.room.TypeConverters(TypeConverter::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        private const val DATABASE_NAME = "article_database"
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        @Volatile
        private var instance: ArticleDatabase? = null

        fun createDatabase(context: Context): ArticleDatabase? {
            if (instance == null) {
                synchronized(ArticleDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java,
                        DATABASE_NAME
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }

        fun destroyDatabase() {
            instance = null
        }
    }
}