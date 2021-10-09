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
import com.lanh.uefachampions.screen.detialnews.DetailNewsFragment
import com.lanh.uefachampions.screen.news.adapter.NewsAdapter
import com.lanh.uefachampions.screen.teamdetail.TeamDetailFragment
import com.lanh.uefachampions.utils.LoadingFragment
import com.lanh.uefachampions.utils.addFragment
import com.lanh.uefachampions.utils.hideKeyboard
import com.lanh.uefachampions.utils.replaceFragment
import kotlinx.android.synthetic.main.fragment_fixtures.*
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment(), ContractNews.View {

    private val newsAdapter by lazy { NewsAdapter() }
    private val newsPresenter: ContractNews.Presenter by lazy {
        NewsPresenter(
            NewsRepository.instance
        )
    }

    private val loadingView by lazy {
        LoadingFragment.newInstance()
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
        initView()
        onClickItem()
        reloadData()
    }

    override fun onGetNewsSuccess(news: MutableList<News>) {
        newsAdapter.updateData(news)
        swipeRefreshDataNews.isRefreshing = false
    }

    override fun showLoading() {
        addFragment(loadingView, R.id.containerLayout)
    }

    override fun hiddenLoading() {
        fragmentManager?.popBackStack()
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initPresenter() {
        newsPresenter.apply {
            setView(this@NewsFragment)
            getNews()
        }
    }

    private fun initView() {
        recyclerViewNews.apply {
            adapter = this@NewsFragment.newsAdapter
        }
    }

    private fun reloadData() {
        swipeRefreshDataNews.setOnRefreshListener {
            newsPresenter.apply {
                getNews()
            }
        }
    }

    private fun onClickItem() {
        newsAdapter.apply {
            registerNewsClickListener {
                addFragment(
                    DetailNewsFragment(), R.id.containerLayout
                )
            }
        }
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
