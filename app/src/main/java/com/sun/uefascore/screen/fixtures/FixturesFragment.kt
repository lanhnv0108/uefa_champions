package com.sun.uefascore.screen.fixtures

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.uefascore.R
import com.sun.uefascore.data.source.repository.FixtureRepository
import com.sun.uefascore.utils.Constant
import java.text.SimpleDateFormat
import java.util.*

class FixturesFragment : Fragment() {

    private val presenter: ContractFixture.Presenter by lazy {
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
    }

    private fun initPresenter() {
        val date = Date()
        val formatter = SimpleDateFormat(Constant.DAY_FORMAT)
        val dayDevice = formatter.format(date)
        val season = dayDevice.split("-")
        presenter.apply {
            getFixture(dayDevice , season[0])
        }
    }

    companion object {
        fun newInstance() = FixturesFragment()
    }
}
