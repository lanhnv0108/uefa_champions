package com.lanh.uefachampions.screen.news.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.utils.loadUrl
import kotlinx.android.synthetic.main.item_fixture_of_day.view.*
import kotlinx.android.synthetic.main.item_news_first.view.*

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var onItemClickedNews: ((News) -> Unit)? = null

    fun binData(news: News) {
        itemView.apply {
            textViewNameNews.text = news.title
            textViewTime.text = news.published
            imageViewNews.loadUrl(news.image.toString())
        }
        itemView.setOnClickListener {
            onItemClickedNews?.let { it -> it(news) }
        }
    }

    fun registerNewsClickListener(onItemClickedNews: ((News) -> Unit)?) {
        this.onItemClickedNews = onItemClickedNews
    }
}
