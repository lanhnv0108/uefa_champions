package com.sun.uefascore.screen.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sun.uefascore.R
import com.sun.uefascore.data.model.Fixture
import com.sun.uefascore.data.source.repository.FixtureRepository
import com.sun.uefascore.screen.adapter.FixtureAdapter
import com.sun.uefascore.utils.Constant
import kotlinx.android.synthetic.main.fragment_fixtures.*
import java.text.SimpleDateFormat
import java.util.*

class FixturesFragment : Fragment(), ContractFixture.View {

    private val fixtureAdapter by lazy { FixtureAdapter() }
    private val fixturePresenter: ContractFixture.Presenter by lazy {
        FixturePresenter(
            FixtureRepository.instance
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fixtures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initDate()
        initView()
        searchTeam()
        onClickItem()
    }

    private fun initPresenter() {
        val date = Date()
        val formatter = SimpleDateFormat(Constant.DAY_FORMAT)
        val dayDevice = formatter.format(date)
        val season = dayDevice.split("-")
        fixturePresenter.apply {
            setView(this@FixturesFragment)
            getFixture(dayDevice, season[0])
        }
    }

    private fun initView() {
        recyclerViewFixtureOfDay.apply {
            adapter = this@FixturesFragment.fixtureAdapter
        }
    }

    private fun initDate() {
        val date = Date()
        val formatter = SimpleDateFormat(Constant.DAY_FORMAT)
        val dayDevice = formatter.format(date)
        textViewDayOfMatches.text = dayDevice
    }

    private fun searchTeam() {
        searchViewTeam.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }

    private fun onClickItem() {
        fixtureAdapter.apply {
            registerHomeClickListener { }
            registerAwayClickListener { }
        }
    }

    override fun onGetFixtureSuccess(fixtures: MutableList<Fixture>) {
        fixtureAdapter.updateData(fixtures)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = FixturesFragment()
    }
}
