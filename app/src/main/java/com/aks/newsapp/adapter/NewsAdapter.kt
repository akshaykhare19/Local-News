package com.aks.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.aks.newsapp.databinding.ListItemBinding
import com.aks.newsapp.model.ArticlesItem
import com.bumptech.glide.Glide

class NewsAdapter(
    private val articles : LiveData<List<ArticlesItem>>,
    private val listener : NewsArticleClicked
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val adapterLayout = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = articles.value!![position]
        holder.bindData(currentItem)

        holder.itemView.setOnClickListener {
            listener.onArticleClicked(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return articles.value!!.size
    }

    class NewsViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(item : ArticlesItem){
            binding.itemHeadline.text = item.headline
            binding.itemDate.text = item.postedDate

            Glide.with(this.itemView.context).load(item.imageUrl).into(binding.itemImage)
        }
    }

    interface NewsArticleClicked {
        fun onArticleClicked(item: ArticlesItem)
    }

}