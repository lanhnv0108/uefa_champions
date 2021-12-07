package com.lanh.uefachampions.screen.news

import android.widget.Toast
import androidx.core.view.isVisible
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.data.source.repository.NewsRepository
import com.lanh.uefachampions.databinding.FragmentNewsBinding
import com.lanh.uefachampions.screen.base.BaseFragment
import com.lanh.uefachampions.screen.detialnews.DetailNewsFragment
import com.lanh.uefachampions.screen.news.adapter.NewsAdapter
import com.lanh.uefachampions.utils.addFragment
import kotlinx.android.synthetic.main.fragment_fixtures.*
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment<FragmentNewsBinding, ContractNews.Presenter>(),
    ContractNews.View {
    override val layoutId: Int
        get() = R.layout.fragment_news
    private val newsAdapter by lazy { NewsAdapter() }
    override val presenter: ContractNews.Presenter by lazy {
        NewsPresenter(
            NewsRepository.instance
        )
    }

    override fun initView() {
        super.initView()
        initPresenter()
        onClickItem()
        reloadData()
        recyclerViewNews.adapter = newsAdapter

    }

    override fun onGetNewsSuccess(news: MutableList<News>) {
        newsAdapter.updateData(news)
        swipeRefreshDataNews.isRefreshing = false
    }

    override fun showLoading() {
        binding.layoutLoading.isVisible = true
    }

    override fun hiddenLoading() {
        binding.layoutLoading.isVisible = false
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message ?: "null", Toast.LENGTH_SHORT).show()
    }

    override fun initPresenter() {
        presenter.apply {
            setView(this@NewsFragment)
            getNews()
        }
    }

    private fun reloadData() {
        swipeRefreshDataNews.setOnRefreshListener {
            presenter.apply {
                getNews()
            }
        }
    }

    private fun onClickItem() {
        newsAdapter.apply {
            registerNewsClickListener {
                addFragment(
                    DetailNewsFragment.newInstance(it.slug), R.id.containerLayout
                )
            }
        }
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
