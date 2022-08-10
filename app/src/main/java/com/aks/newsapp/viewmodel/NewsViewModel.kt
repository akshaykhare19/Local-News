package com.aks.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.newsapp.model.NewsItem
import com.aks.newsapp.utils.NewsService
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _article = MutableLiveData<NewsItem>()

    val article : LiveData<NewsItem> = _article

    init {
        getNewsArticle()
    }

    private fun getNewsArticle() {
        viewModelScope.launch {
            try {
                _article.value = NewsService.newsInstance.getNews("muzaffarnagar")
                Log.d("Status Check", "News Fetching Successful")
            } catch (e : Exception) {
                Log.d("Status Check", e.message.toString())
            }
        }
    }

}