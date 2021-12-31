package com.lanh.uefachampions.screen.standing

import android.widget.Toast
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.StandingLeague
import com.lanh.uefachampions.data.model.Team
import com.lanh.uefachampions.data.source.repository.StandingRepository
import com.lanh.uefachampions.databinding.FragmentStandingBinding
import com.lanh.uefachampions.screen.base.BaseFragment
import com.lanh.uefachampions.screen.standing.adapter.StandingGroupAdapter
import com.lanh.uefachampions.screen.teamdetail.TeamDetailFragment
import com.lanh.uefachampions.utils.OnFavoriteListener
import com.lanh.uefachampions.utils.addFragment
import kotlinx.android.synthetic.main.fragment_standing.*
import kotlin.Exception

class StandingFragment : BaseFragment<FragmentStandingBinding, StandingContract.Presenter>(),
    StandingContract.View{
    override val layoutId: Int
        get() = R.layout.fragment_standing
    override val presenter by lazy {
        StandingPresenter(StandingRepository.instance)
    }
    private val adapter: StandingGroupAdapter by lazy {
        StandingGroupAdapter(::onItemTeamClick)
    }
    private var onFavoriteListener: OnFavoriteListener? = null

    private var season = "2021"

    override fun onGetStandingLeagueSuccess(standingLeague: StandingLeague) {
        adapter.submitList(standingLeague.standingGroups)
        swipeRefreshData.isRefreshing = false
    }

    override fun onError(exception: Exception) {
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }

    private fun onItemTeamClick(item: Team) {
        addFragment(
            TeamDetailFragment.newInstance(
                item.id.toString(),
                season
            ),
            R.id.containerLayout
        )
    }

    override fun onDestroy() {
        onFavoriteListener = null
        super.onDestroy()
    }

    override fun initView() {
        recyclerViewStandingGroup.adapter = adapter
    }

    fun updateSeason(season: String) {
        this.season = season
        getStandingLeague()
    }

    override fun initPresenter() {
        super.initPresenter()
        presenter.run {
            setView(this@StandingFragment)
            getStandingLeague(season)
        }
    }

    override fun handlerEvent() {
        super.handlerEvent()
        swipeRefreshData.setOnRefreshListener {
            getStandingLeague()
        }
    }

    private fun getStandingLeague() {
        presenter.getStandingLeague(season)
    }

    companion object {
        fun newInstance() = StandingFragment()
    }
}
