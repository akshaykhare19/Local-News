package com.aks.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.newsapp.model.News
import com.aks.newsapp.utils.API_KEY
import com.aks.newsapp.utils.NewsService
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _article = MutableLiveData<News>()

    val article : LiveData<News> = _article

    init {
        getNewsArticle()
    }

    private fun getNewsArticle() {
        viewModelScope.launch {
            try {
                _article.value = NewsService.newsInstance.getNews("in", API_KEY)
                Log.d("Status Check", "News Fetching Successful")
            } catch (e : Exception) {
                Log.d("Status Check", "Error in network call : ${e.message}")
            }
        }
    }

}