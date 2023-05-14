package com.example.newsapp.api

import com.example.newsapp.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        var retrofitService: RetrofitInstance? = null

        fun getInstance() : RetrofitInstance {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitInstance::class.java)
            }
            return retrofitService!!
        }
    }
}