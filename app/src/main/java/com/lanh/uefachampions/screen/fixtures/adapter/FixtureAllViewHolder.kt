package com.lanh.uefachampions.screen.fixtures.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lanh.uefachampions.data.model.Fixture
import com.lanh.uefachampions.utils.loadImageUrl
import kotlinx.android.synthetic.main.item_fixture_history.view.*
import kotlinx.android.synthetic.main.item_fixture_of_day.view.imageViewTeamHome
import kotlinx.android.synthetic.main.item_fixture_of_day.view.imageViewTeamWay
import kotlinx.android.synthetic.main.item_fixture_of_day.view.textViewNameAway
import kotlinx.android.synthetic.main.item_fixture_of_day.view.textViewNameHome

@Suppress("NAME_SHADOWING")
class FixtureAllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var onItemClickedHome: ((Fixture) -> Unit)? = null
    private var onItemClickedAway: ((Fixture) -> Unit)? = null

    @SuppressLint("SetTextI18n")
    fun binData(fixture: Fixture) {
        itemView.apply {
            textViewDayOfMatches?.text = fixture.date
            textViewNameHome?.text = fixture.home?.name
            textViewNameAway?.text = fixture.away?.name
            textViewGoalsHome?.text = fixture.goals?.home
            textViewGoalsAway?.text = fixture.goals?.away
            fixture.home?.logo?.let { imageViewTeamHome?.loadImageUrl(it) }
            fixture.away?.logo?.let { imageViewTeamWay?.loadImageUrl(it) }
            imageViewTeamHome.setOnClickListener {
                onItemClickedHome?.let { it -> it(fixture) }
            }
            imageViewTeamWay.setOnClickListener {
                onItemClickedAway?.let { it -> it(fixture) }
            }
        }
    }

    fun registerHomeClickListener(onItemClickedHome: ((Fixture) -> Unit)?) {
        this.onItemClickedHome = onItemClickedHome
    }

    fun registerAwayClickListener(onItemClickedAway: ((Fixture) -> Unit)?) {
        this.onItemClickedAway = onItemClickedAway
    }
}
