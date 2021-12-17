package com.lanh.uefachampions.screen.fixtures

import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ConcatAdapter
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.FixtureSeason
import com.lanh.uefachampions.data.source.repository.FixtureRepository
import com.lanh.uefachampions.databinding.FragmentFixturesBinding
import com.lanh.uefachampions.screen.base.BaseFragment
import com.lanh.uefachampions.screen.fixturedetail.FixtureDetailFragment
import com.lanh.uefachampions.screen.fixtures.adapter.DateSeasonAdapter
import com.lanh.uefachampions.screen.fixtures.adapter.ScheduleAdapter
import com.lanh.uefachampions.screen.item.ItemDate
import com.lanh.uefachampions.screen.item.ItemTeamMatchSchedule
import com.lanh.uefachampions.screen.item.ItemTextTitleSchedule
import com.lanh.uefachampions.utils.*
import org.joda.time.DateTime
import java.util.*

class FixturesFragment : BaseFragment<FragmentFixturesBinding, ContractFixture.Presenter>(),
    ContractFixture.View, OnFavoriteListener {
    override val layoutId: Int
        get() = R.layout.fragment_fixtures

    override val presenter: ContractFixture.Presenter by lazy {
        FixturePresenter(FixtureRepository.instance)
    }

    private var season = ""
    private var onFavoriteListener: OnFavoriteListener? = null
    private var onGetSeasonListener: OnGetSeasonListener? = null
    private var dateCurrent: DateTime = DateTime.now()

    private val dateSeasonAdapter by lazy {
        DateSeasonAdapter(::dateSelected)
    }

    private val scheduleAdapter by lazy {
        ScheduleAdapter(::goToFixtureDetail)
    }

    private val adapter by lazy {
        ConcatAdapter(dateSeasonAdapter, scheduleAdapter)
    }

    override fun initView() {
        super.initView()
        presenter.setView(this)
        initSwipeRefreshLayout()
        binding.rcvFixtures.adapter = adapter
        binding.rcvFixtures.itemAnimator = null
        dateSeasonAdapter.submitItem(ItemDate(dateCurrent, true))
        dateSelected(dateCurrent)
    }

    override fun onGetFixtureSuccess(fixtures: MutableList<FixtureSeason>) {
        scheduleAdapter.submitList(
            scheduleAdapter.currentList.toMutableList().apply {
                addAll(fixtures.map { it.mapItemSchedule() })
            }
        )
    }

    override fun onGetAllFixtureSuccess(fixtures: MutableList<FixtureSeason>) {
        binding.layoutLoading.isVisible = false
        binding.swipeRefreshData.isRefreshing = false
        scheduleAdapter.submitList(
            scheduleAdapter.currentList.toMutableList().apply {
                add(ItemTextTitleSchedule(null))
                addAll(fixtures.map { it.mapItemSchedule() })
            }
        ) {
            binding.rcvFixtures.scrollToPosition(0)
        }
    }

    override fun getSeasonSuccess(season: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
        Log.e("aaa", "$exception")
    }

    override fun onClickFavoriteListener() {
        onFavoriteListener?.onClickFavoriteListener()
    }

    fun registerFavoriteListener(onFavoriteListener: OnFavoriteListener) {
        this.onFavoriteListener = onFavoriteListener
    }

    fun registerGetSeasonListener(onGetSeasonListener: OnGetSeasonListener) {
        this.onGetSeasonListener = onGetSeasonListener
    }

    private fun initSwipeRefreshLayout() {
        binding.swipeRefreshData.setOnRefreshListener {
            dateSelected(dateTime = dateCurrent)
        }
    }

    private fun dateSelected(dateTime: DateTime) {
        dateCurrent = dateTime
        dateSeasonAdapter.currentList.firstOrNull()?.date = dateTime
        presenter.getFixture(dateTime.toString("YYYY-MM-dd", Locale.US), "2021")
        presenter.getAllFixture("2021")
        binding.layoutLoading.isVisible = true
        scheduleAdapter.submitItem(ItemTextTitleSchedule(dateTime))
    }

    private fun goToFixtureDetail(itemTeamMatchSchedule: ItemTeamMatchSchedule) {
        addFragment(FixtureDetailFragment.newInstance(itemTeamMatchSchedule), R.id.containerLayout)
    }

    companion object {
        fun newInstance() = FixturesFragment()
    }
}
