package com.lanh.uefachampions.screen.fixturedetail

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.ConcatAdapter
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.FixtureDetailData
import com.lanh.uefachampions.data.model.mapToInfoFixtureDetailItem
import com.lanh.uefachampions.data.model.mapToTopFixtureDetailItem
import com.lanh.uefachampions.data.source.repository.FixtureRepository
import com.lanh.uefachampions.databinding.FragmentFixtureDetailBinding
import com.lanh.uefachampions.screen.base.BaseFragment
import com.lanh.uefachampions.screen.fixturedetail.adapter.InfoFixtureDetailAdapter
import com.lanh.uefachampions.screen.fixturedetail.adapter.TopFixtureDetailAdapter
import com.lanh.uefachampions.screen.item.ItemTeamMatchSchedule

class FixtureDetailFragment :
    BaseFragment<FragmentFixtureDetailBinding, FixtureDetailContact.Presenter>(),
    FixtureDetailContact.View {
    override val layoutId: Int
        get() = R.layout.fragment_fixture_detail
    override val presenter: FixtureDetailContact.Presenter by lazy {
        FixtureDetailPresenter(FixtureRepository.instance)
    }

    private var itemTeamMatchSchedule: ItemTeamMatchSchedule? = null

    private val topFixtureDetailAdapter by lazy {
        TopFixtureDetailAdapter()
    }

    private val infoFixtureDetailAdapter by lazy {
        InfoFixtureDetailAdapter()
    }

    private val adapter by lazy {
        ConcatAdapter(topFixtureDetailAdapter, infoFixtureDetailAdapter)
    }

    override fun initView() {
        itemTeamMatchSchedule = arguments?.getParcelable(ARG_ID_FIXTURE)
        initSwipeRefreshLayout()
        binding.rcvFixtureDetail.adapter = adapter
    }

    override fun initPresenter() {
        presenter.setView(this)
        itemTeamMatchSchedule?.id?.let { presenter.getFixtureDetail(it) }
    }

    override fun handlerEvent() {
        binding.btnBack.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    override fun onGetFixtureDetailSuccess(fixtureDetailData: List<FixtureDetailData>?) {
        binding.swipeRefreshData.isRefreshing = false
        binding.isEmptyContent = fixtureDetailData.isNullOrEmpty()
        topFixtureDetailAdapter.submitItem(fixtureDetailData?.mapToTopFixtureDetailItem(itemTeamMatchSchedule?.goal))
        infoFixtureDetailAdapter.submitItem(fixtureDetailData?.mapToInfoFixtureDetailItem())
    }

    override fun onLoading(isLoading: Boolean) {
        binding.isShowLoading = isLoading
    }

    override fun onError(error: Throwable?) {
        binding.swipeRefreshData.isRefreshing = false
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_SHORT).show()
    }

    private fun initSwipeRefreshLayout() {
        binding.swipeRefreshData.setOnRefreshListener {
            itemTeamMatchSchedule?.id?.let { presenter.getFixtureDetail(it, false) }
        }
    }

    companion object {
        private const val ARG_ID_FIXTURE = "ARG_ID_FIXTURE"

        fun newInstance(itemTeamMatchSchedule: ItemTeamMatchSchedule) = FixtureDetailFragment().apply {
            arguments = bundleOf(
                ARG_ID_FIXTURE to itemTeamMatchSchedule
            )
        }
    }
}
