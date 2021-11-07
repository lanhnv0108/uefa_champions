package com.lanh.uefachampions.screen.fixtures

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.Fixture
import com.lanh.uefachampions.data.source.repository.FixtureRepository
import com.lanh.uefachampions.databinding.FragmentFixturesBinding
import com.lanh.uefachampions.screen.base.BaseFragment
import com.lanh.uefachampions.screen.fixtures.adapter.DateAdapter
import com.lanh.uefachampions.screen.fixtures.adapter.FixtureAdapter
import com.lanh.uefachampions.screen.fixtures.adapter.FixtureAllAdapter
import com.lanh.uefachampions.screen.searchteam.SearchFragment
import com.lanh.uefachampions.screen.teamdetail.TeamDetailFragment
import com.lanh.uefachampions.utils.*
import com.lanh.uefachampions.utils.datetime.getListDateInMonth
import kotlinx.android.synthetic.main.fragment_fixtures.*
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.util.*

class FixturesFragment : BaseFragment<FragmentFixturesBinding, ContractFixture.Presenter>(),
    ContractFixture.View, AdapterView.OnItemSelectedListener,
    OnFavoriteListener {
    override val layoutId: Int
        get() = R.layout.fragment_fixtures

    override val presenter: ContractFixture.Presenter by lazy {
        FixturePresenter(
            FixtureRepository.instance
        )
    }

    private var season = ""
    private var dayByPicker = ""
    private var dayDevice = ""
    private var onFavoriteListener: OnFavoriteListener? = null
    private var onGetSeasonListener: OnGetSeasonListener? = null
    private val fixtureAdapter by lazy { FixtureAdapter() }
    private val fixtureAllAdapter by lazy { FixtureAllAdapter() }

    private val dateAdapter by lazy {
        DateAdapter({

        })
    }

    override fun onClickFavoriteListener() {
        onFavoriteListener?.onClickFavoriteListener()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initDate()
        searchTeam()
        onClickItem()
        onClickDatePicker()
        reloadData()
        constraintLayoutFixtures.setOnClickListener {
            hideKeyboard()
        }
    }

    override fun onGetFixtureSuccess(fixtures: MutableList<Fixture>) {
        fixtureAdapter.updateData(fixtures)
    }

    override fun onGetAllFixtureSuccess(fixtures: MutableList<Fixture>) {
        fixtureAllAdapter.updateData(fixtures)
        swipeRefreshData.isRefreshing = false
    }

    override fun getSeasonSuccess(season: MutableList<String>) {
        season.reverse()
        initSpinner(season)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SimpleDateFormat")
    override fun initPresenter() {
        val date = Date()
        val formatter = SimpleDateFormat(Constant.DAY_FORMAT)
        dayDevice = formatter.format(date)
        presenter.apply {
            setView(this@FixturesFragment)
            getFixture(dayDevice, season)
            getSeason()
        }
    }

    override fun initView() {
        rcvDatePicker.adapter = dateAdapter
        recyclerViewFixtureOfDay.apply {
            adapter = this@FixturesFragment.fixtureAdapter
        }
        recyclerViewFixtureHistory.apply {
            setHasFixedSize(true)
            adapter = this@FixturesFragment.fixtureAllAdapter

        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun initDate() {
        val date = Date()
        dateAdapter.submitList(DateTime.now().getListDateInMonth())
        val formatter = SimpleDateFormat(Constant.DAY_FORMAT)
        val dayDevice = formatter.format(date)
        dayByPicker = dayDevice
        textViewDayOfMatches.text = dayDevice
    }

    fun registerGetSeasonListener(onGetSeasonListener: OnGetSeasonListener) {
        this.onGetSeasonListener = onGetSeasonListener
    }

    private fun searchTeam() {
        searchViewTeam.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String?): Boolean {
                addFragment(SearchFragment.newInstance(name?.trim(), season), R.id.containerLayout)
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }

    private fun onClickItem() {
        fixtureAdapter.apply {
            registerHomeClickListener {
                addFragment(
                    TeamDetailFragment.newInstance(it.home?.id.toString(), season).apply {
                        registerFavoriteListener(this@FixturesFragment)
                    },
                    R.id.containerLayout
                )
                hideKeyboard()
            }
            registerAwayClickListener {
                addFragment(
                    TeamDetailFragment.newInstance(it.away?.id.toString(), season).apply {
                        registerFavoriteListener(this@FixturesFragment)
                    },
                    R.id.containerLayout
                )
                hideKeyboard()
            }
        }
        fixtureAllAdapter.apply {
            registerHomeClickListener {
                addFragment(
                    TeamDetailFragment.newInstance(it.home?.id.toString(), season).apply {
                        registerFavoriteListener(this@FixturesFragment)
                    },
                    R.id.containerLayout
                )
                hideKeyboard()
            }
            registerAwayClickListener {
                addFragment(
                    TeamDetailFragment.newInstance(it.away?.id.toString(), season).apply {
                        registerFavoriteListener(this@FixturesFragment)
                    },
                    R.id.containerLayout
                )
                hideKeyboard()
            }
        }
    }

    private fun onClickDatePicker() {
        imageViewCalendar.setOnClickListener {
            onGetDataByDatePicker()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun onGetDataByDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker =
            context?.let {
                DatePickerDialog(it, { _, year, monthOfYear, dayOfMonth ->
                    val stringDate = SimpleDateFormat(Constant.DAY_FORMAT).format(
                        SimpleDateFormat(
                            Constant.DAY_FORMAT_FORWARD
                        ).parse("$year/${monthOfYear + 1}/$dayOfMonth")
                    )
                    dayByPicker = stringDate
                    textViewDayOfMatches.text = stringDate
                    presenter.apply {
                        setView(this@FixturesFragment)
                        getFixture(stringDate, season)
                    }
                }, year, month, day)
            }
        datePicker?.show()
    }

    private fun initSpinner(seasons: MutableList<String>) {
        val stateAdapter = view?.let {
            ArrayAdapter(
                it.context,
                R.layout.support_simple_spinner_dropdown_item,
                seasons
            )
        }
        spinnerSeason?.adapter = stateAdapter
        spinnerSeason?.onItemSelectedListener = this
    }

    fun registerFavoriteListener(onFavoriteListener: OnFavoriteListener) {
        this.onFavoriteListener = onFavoriteListener
    }

    private fun reloadData() {
        swipeRefreshData.setOnRefreshListener {
            presenter.apply {
                getAllFixture(season)
            }
        }
    }

    override fun onItemSelected(p: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val seasonSpinner = p?.selectedItem.toString()
        textViewSeason.text = seasonSpinner
        season = seasonSpinner
        presenter.apply {
            setView(this@FixturesFragment)
            getAllFixture(season)
            getFixture(dayByPicker, season)
        }
        onGetSeasonListener?.getSeason(seasonSpinner)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    companion object {
        fun newInstance() = FixturesFragment()
    }
}
