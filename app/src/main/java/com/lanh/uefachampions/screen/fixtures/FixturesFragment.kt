package com.lanh.uefachampions.screen.fixtures

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ConcatAdapter
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.Fixture
import com.lanh.uefachampions.data.source.repository.FixtureRepository
import com.lanh.uefachampions.databinding.FragmentFixturesBinding
import com.lanh.uefachampions.screen.base.BaseFragment
import com.lanh.uefachampions.screen.fixtures.adapter.DateSeasonAdapter
import com.lanh.uefachampions.screen.fixtures.adapter.ScheduleAdapter
import com.lanh.uefachampions.screen.item.ItemDate
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

    override fun onGetFixtureSuccess(fixtures: MutableList<Fixture>) {
        scheduleAdapter.submitList(
            scheduleAdapter.currentList.toMutableList().apply {
                addAll(fixtures.map { it.mapItemSchedule() })
            }
        )
    }

    override fun onGetAllFixtureSuccess(fixtures: MutableList<Fixture>) {
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

    private fun goToFixtureDetail(id: Int) {
        // TODO Navigation SEASON MATCH DETAIL
        Toast.makeText(requireContext(), "Item $id", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = FixturesFragment()
    }
}

//
//
//override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//    initPresenter()
//    initDate()
//    searchTeam()
//    onClickItem()
//    onClickDatePicker()
//    reloadData()
//    constraintLayoutFixtures.setOnClickListener {
//        hideKeyboard()
//    }
//}
//
//override fun onGetFixtureSuccess(fixtures: MutableList<Fixture>) {
//    fixtureAdapter.updateData(fixtures)
//}
//
//override fun onGetAllFixtureSuccess(fixtures: MutableList<Fixture>) {
//    fixtureAllAdapter.updateData(fixtures)
//    swipeRefreshData.isRefreshing = false
//}
//
//override fun getSeasonSuccess(season: MutableList<String>) {
//    season.removeAt(season.lastIndex)
//    season.removeAt(season.lastIndex)
//    season.reverse()
//    initSpinner(season)
//}
//
//override fun onError(exception: Exception?) {
//    Toast.makeText(context, exception?.message ?: "", Toast.LENGTH_SHORT).show()
//}
//
//@SuppressLint("SimpleDateFormat")
//override fun initPresenter() {
//    val date = Date()
//    val formatter = SimpleDateFormat(Constant.DAY_FORMAT)
//    dayDevice = formatter.format(date)
//    presenter.apply {
//        setView(this@FixturesFragment)
//        getFixture(dayDevice, season)
//        getSeason()
//    }
//}
//
//override fun initView() {
//    rcvDatePicker.adapter = dateAdapter
//    recyclerViewFixtureOfDay.apply {
//        adapter = this@FixturesFragment.fixtureAdapter
//    }
//    recyclerViewFixtureHistory.apply {
//        setHasFixedSize(true)
//        adapter = this@FixturesFragment.fixtureAllAdapter
//
//    }
//}
//
//@SuppressLint("SimpleDateFormat")
//private fun initDate() {
//    val date = Date()
//    dateAdapter.submitList(DateTime.now().getListDateInMonth())
//    val formatter = SimpleDateFormat(Constant.DAY_FORMAT)
//    val dayDevice = formatter.format(date)
//    dayByPicker = dayDevice
//    textViewDayOfMatches.text = dayDevice
//}
//
//
//private fun searchTeam() {
//    searchViewTeam.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//        override fun onQueryTextSubmit(name: String?): Boolean {
//            addFragment(SearchFragment.newInstance(name?.trim(), season), R.id.containerLayout)
//            hideKeyboard()
//            return true
//        }
//
//        override fun onQueryTextChange(p0: String?): Boolean {
//            return true
//        }
//    })
//}
//
//private fun onClickItem() {
//    fixtureAdapter.apply {
//        registerHomeClickListener {
//            addFragment(
//                TeamDetailFragment.newInstance(it.home?.id.toString(), season).apply {
//                    registerFavoriteListener(this@FixturesFragment)
//                },
//                R.id.containerLayout
//            )
//            hideKeyboard()
//        }
//        registerAwayClickListener {
//            addFragment(
//                TeamDetailFragment.newInstance(it.away?.id.toString(), season).apply {
//                    registerFavoriteListener(this@FixturesFragment)
//                },
//                R.id.containerLayout
//            )
//            hideKeyboard()
//        }
//    }
//    fixtureAllAdapter.apply {
//        registerHomeClickListener {
//            addFragment(
//                TeamDetailFragment.newInstance(it.home?.id.toString(), season).apply {
//                    registerFavoriteListener(this@FixturesFragment)
//                },
//                R.id.containerLayout
//            )
//            hideKeyboard()
//        }
//        registerAwayClickListener {
//            addFragment(
//                TeamDetailFragment.newInstance(it.away?.id.toString(), season).apply {
//                    registerFavoriteListener(this@FixturesFragment)
//                },
//                R.id.containerLayout
//            )
//            hideKeyboard()
//        }
//    }
//}
//
//private fun onClickDatePicker() {
//    imageViewCalendar.setOnClickListener {
//        onGetDataByDatePicker()
//    }
//}
//
//@SuppressLint("SimpleDateFormat")
//private fun onGetDataByDatePicker() {
//    val calendar = Calendar.getInstance()
//    val year = calendar.get(Calendar.YEAR)
//    val month = calendar.get(Calendar.MONTH)
//    val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//    val datePicker =
//        context?.let {
//            DatePickerDialog(it, { _, year, monthOfYear, dayOfMonth ->
//                val stringDate = SimpleDateFormat(Constant.DAY_FORMAT).format(
//                    SimpleDateFormat(
//                        Constant.DAY_FORMAT_FORWARD
//                    ).parse("$year/${monthOfYear + 1}/$dayOfMonth")
//                )
//                dayByPicker = stringDate
//                textViewDayOfMatches.text = stringDate
//                presenter.apply {
//                    setView(this@FixturesFragment)
//                    getFixture(stringDate, season)
//                }
//            }, year, month, day)
//        }
//    datePicker?.show()
//}
//
//private fun initSpinner(seasons: MutableList<String>) {
//    val stateAdapter = view?.let {
//        ArrayAdapter(
//            it.context,
//            R.layout.support_simple_spinner_dropdown_item,
//            seasons
//        )
//    }
//    spinnerSeason?.adapter = stateAdapter
//    spinnerSeason?.onItemSelectedListener = this
//}

//
//override fun onItemSelected(p: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//    val seasonSpinner = p?.selectedItem.toString()
//    textViewSeason.text = seasonSpinner
//    season = seasonSpinner
//    presenter.apply {
//        setView(this@FixturesFragment)
//        getAllFixture(season)
//        getFixture(dayByPicker, season)
//    }
//    onGetSeasonListener?.getSeason(seasonSpinner)
//}
//
//override fun onNothingSelected(p0: AdapterView<*>?) {}
