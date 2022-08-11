package com.aks.newsapp.model

data class News(
    val articles : List<Article>
)

data class Article(
    val author : String,
    val title : String,
    val url : String,
    val urlToImage : String
)
