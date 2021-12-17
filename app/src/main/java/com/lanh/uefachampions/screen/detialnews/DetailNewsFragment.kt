package com.lanh.uefachampions.screen.detialnews

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.NewsDetail
import com.lanh.uefachampions.data.source.repository.NewsDetailRepository
import com.lanh.uefachampions.databinding.FragmentDetailNewsBinding
import com.lanh.uefachampions.screen.base.BaseFragment
import com.lanh.uefachampions.utils.loadUrl
import kotlinx.android.synthetic.main.fragment_detail_news.*
import kotlinx.android.synthetic.main.item_news_first.view.*

class DetailNewsFragment : BaseFragment<FragmentDetailNewsBinding, ContractNewsDetail.Presenter>(),
    ContractNewsDetail.View {
    override val layoutId: Int
        get() = R.layout.fragment_detail_news
    override val presenter: ContractNewsDetail.Presenter by lazy {
        NewsDetailPresenter(
            NewsDetailRepository.instance
        )
    }

    override fun onGetNewsDetailSuccess(newsDetail: NewsDetail) {
        binding.newDetails = newsDetail
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun initPresenter() {
        presenter.apply {
            setView(this@DetailNewsFragment)
            arguments?.getString(BUNDLE_SLUG)?.let { getDetailNews(it) }
        }
    }

    companion object {
        private const val BUNDLE_SLUG = "BUNDLE_SLUG"
        fun newInstance(slug: String?) = DetailNewsFragment().apply {
            arguments = bundleOf(
                BUNDLE_SLUG to slug
            )
        }
    }
}
