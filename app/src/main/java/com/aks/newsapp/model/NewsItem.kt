package com.aks.newsapp.model

data class NewsItem(val category: String = "",
                    val articles: List<ArticlesItem>,
                    val totalResults: Int = 0
)