package com.lanh.uefachampions.screen.topscorers

import android.util.Log
import android.widget.Toast
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.TopScorer
import com.lanh.uefachampions.data.source.repository.TopScorerRepository
import com.lanh.uefachampions.databinding.FragmentTopScorersBinding
import com.lanh.uefachampions.screen.base.BaseFragment
import com.lanh.uefachampions.screen.playerdetail.PlayerDetailFragment
import com.lanh.uefachampions.screen.topscorers.adapter.TopScorersAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_top_scorers.*
import kotlinx.android.synthetic.main.fragment_top_scorers.swipeRefreshData
import java.lang.Exception

class TopScorersFragment : BaseFragment<FragmentTopScorersBinding, TopScorersContract.Presenter>(),
    TopScorersContract.View {
    override val layoutId: Int
        get() = R.layout.fragment_top_scorers
    override val presenter by lazy {
        TopScorersPresenter(TopScorerRepository.instance)
    }
    private val adapter: TopScorersAdapter by lazy {
        TopScorersAdapter(::onItemPlayClick)
    }
    private var season: String? = "2021"

    fun updateSeason(season: String) {
        this.season = season
        presenter.getTopScorer(season)
    }

    override fun onGetTopScorerSuccess(topScorers: MutableList<TopScorer>) {
        Log.e("xxx" , topScorers.size.toString())
        adapter.submitList(topScorers.mapIndexed { index, topScorer -> topScorer.copy(index = "${index + 1}") })
        swipeRefreshData.isRefreshing = false
    }

    override fun onError(exception: Exception) {
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }

    private fun onItemPlayClick(item: TopScorer) {
        item.player?.let {
            PlayerDetailFragment.newInstance(it)
                .show(this@TopScorersFragment.childFragmentManager, null)
        }
    }

    override fun initView() {
        recyclerViewTopScorers.adapter = adapter
        reloadData()
    }

    override fun initPresenter() {
        presenter.run {
            setView(this@TopScorersFragment)
            season?.let { getTopScorer(it) }
        }
    }

    private fun reloadData() {
        swipeRefreshData.setOnRefreshListener {
            season?.let { presenter.getTopScorer(it) }
        }
    }

    companion object {
        fun newInstance() = TopScorersFragment()
    }
}
