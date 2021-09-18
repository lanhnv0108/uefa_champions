package com.lanh.uefachampions.screen.standing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.StandingLeague
import com.lanh.uefachampions.data.model.Team
import com.lanh.uefachampions.data.source.repository.StandingRepository
import com.lanh.uefachampions.screen.standing.adapter.StandingGroupAdapter
import com.lanh.uefachampions.screen.teamdetail.TeamDetailFragment
import com.lanh.uefachampions.utils.OnFavoriteListener
import com.lanh.uefachampions.utils.OnItemRecyclerViewClickListener
import com.lanh.uefachampions.utils.addFragment
import kotlinx.android.synthetic.main.fragment_standing.*
import kotlin.Exception

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class StandingFragment : Fragment(),
    StandingContract.View,
    OnItemRecyclerViewClickListener<Team>, OnFavoriteListener {

    private var season = ""
    private val presenter by lazy {
        StandingPresenter(StandingRepository.instance)
    }
    private val adapter: StandingGroupAdapter by lazy {
        StandingGroupAdapter()
    }
    private var onFavoriteListener: OnFavoriteListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        reloadData()
    }

    override fun onGetStandingLeagueSuccess(standingLeague: StandingLeague) {
        adapter.updateData(standingLeague.standingGroups)
        swipeRefreshData.isRefreshing = false
    }

    override fun onError(exception: Exception) {
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClickListener(item: Team?) {
        addFragment(
            TeamDetailFragment.newInstance(
                item?.id.toString(),
                season
            ).apply {
                registerFavoriteListener(this@StandingFragment)
            },
            R.id.containerLayout
        )
    }

    override fun onClickFavoriteListener() {
        onFavoriteListener?.onClickFavoriteListener()
    }

    fun registerFavoriteListener(onFavoriteListener: OnFavoriteListener) {
        this.onFavoriteListener = onFavoriteListener
    }

    private fun initView() {
        recyclerViewStandingGroup.adapter = adapter
        adapter.registerItemRecyclerViewClickListener(this)
    }

    fun updateSeason(season: String) {
        this.season = season
        initData()
    }

    private fun initData() {
        presenter.run {
            setView(this@StandingFragment)
            getStandingLeague(season)
        }
    }

    private fun reloadData() {
        swipeRefreshData.setOnRefreshListener {
            initData()
        }
    }

    companion object {
        fun newInstance() = StandingFragment()
    }
}
