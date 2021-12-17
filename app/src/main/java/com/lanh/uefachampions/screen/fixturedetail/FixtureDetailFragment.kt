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
import com.lanh.uefachampions.utils.Constant.NOT_FOUND

class FixtureDetailFragment :
    BaseFragment<FragmentFixtureDetailBinding, FixtureDetailContact.Presenter>(),
    FixtureDetailContact.View {
    override val layoutId: Int
        get() = R.layout.fragment_fixture_detail
    override val presenter: FixtureDetailContact.Presenter by lazy {
        FixtureDetailPresenter(FixtureRepository.instance)
    }

    private var idFixture: Int? = NOT_FOUND

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
        idFixture = arguments?.getInt(ARG_ID_FIXTURE) ?: NOT_FOUND
        initSwipeRefreshLayout()
        binding.rcvFixtureDetail.adapter = adapter
    }

    override fun initPresenter() {
        presenter.setView(this)
        idFixture?.let { presenter.getFixtureDetail(it) }
    }

    override fun handlerEvent() {
        binding.btnBack.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    override fun onGetFixtureDetailSuccess(fixtureDetailData: List<FixtureDetailData>?) {
        binding.swipeRefreshData.isRefreshing = false
        binding.isEmptyContent = fixtureDetailData.isNullOrEmpty()
        topFixtureDetailAdapter.submitItem(fixtureDetailData?.mapToTopFixtureDetailItem())
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
            idFixture?.let { presenter.getFixtureDetail(it, false) }
        }
    }

    companion object {
        private const val ARG_ID_FIXTURE = "ARG_ID_FIXTURE"

        fun newInstance(idFixture: Int?) = FixtureDetailFragment().apply {
            arguments = bundleOf(
                ARG_ID_FIXTURE to idFixture
            )
        }
    }
}
