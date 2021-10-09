package com.lanh.uefachampions.screen.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.Fixture
import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.utils.Constant

class NewsAdapter() :
    RecyclerView.Adapter<NewsViewHolder>() {

    private val news = mutableListOf<News>()
    private var onItemClickedNews: ((News) -> Unit)? = null

    fun updateData(new: MutableList<News>?) {
        new?.let {
            news.clear()
            news.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun registerNewsClickListener(onItemClickedNews: ((News) -> Unit)?) {
        this.onItemClickedNews = onItemClickedNews
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) Constant.TYPE_HEADER
        else Constant.TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return if (viewType == 1) {
            NewsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_news_first, parent, false)
            ).apply {
                registerNewsClickListener(onItemClickedNews)
            }
        } else {
            NewsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_news_defautl, parent, false)
            ).apply {
                registerNewsClickListener(onItemClickedNews)
            }
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binData(news[position])
    }

    override fun getItemCount() = news.size
}
