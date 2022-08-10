package com.aks.newsapp.utils

import com.aks.newsapp.model.NewsItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://ndtvnews-api.herokuapp.com"

interface NewsApiService {

    @GET("/cities")
    suspend fun getNews(
        @Query("city") city : String
    ) : NewsItem
}

object NewsService {
    val newsInstance : NewsApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsApiService::class.java)
    }
}