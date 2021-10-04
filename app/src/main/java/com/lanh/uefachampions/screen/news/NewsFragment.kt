package com.lanh.uefachampions.screen.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.data.source.repository.NewsRepository

class NewsFragment : Fragment(), ContractNews.View {

    private val newsPresenter: ContractNews.Presenter by lazy {
        NewsPresenter(
            NewsRepository.instance
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
    }

    override fun onGetNewsSuccess(news: MutableList<News>) {
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initPresenter(){

        newsPresenter.apply {
            setView(this@NewsFragment)
            getNews()
        }
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
