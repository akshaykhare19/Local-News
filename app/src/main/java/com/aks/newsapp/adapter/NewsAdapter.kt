package com.aks.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.aks.newsapp.databinding.ListItemBinding
import com.aks.newsapp.model.Article
import com.bumptech.glide.Glide

class NewsAdapter(
    private val newsArticles : LiveData<List<Article>>,
    private val listener : NewsArticleClicked
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val adapterLayout = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsArticles.value!![position]
        holder.bindData(currentItem)

        holder.itemView.setOnClickListener {
            listener.onArticleClicked(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return newsArticles.value!!.size
    }

    class NewsViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(item : Article){
            binding.itemHeadline.text = item.title
            binding.itemAuthor.text = item.author

            Glide.with(this.itemView.context).load(item.urlToImage).into(binding.itemImage)
        }
    }

    interface NewsArticleClicked {
        fun onArticleClicked(item: Article)
    }

}