package com.lanh.uefachampions.screen.searchteam.adapter

import android.os.AsyncTask
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lanh.uefachampions.data.model.TeamDetail
import com.lanh.uefachampions.utils.LoadImageUrl
import com.lanh.uefachampions.utils.loadUrl
import kotlinx.android.synthetic.main.item_team_search.view.*

@Suppress("NAME_SHADOWING")
class SearchTeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var onItemClickTeam: ((TeamDetail) -> Unit)? = null

    fun binData(teamDetail: TeamDetail) {
        itemView.apply {
            textViewNameTeam.text = teamDetail.name
            textViewCountry.text = teamDetail.country
        }
        getImage(teamDetail)
    }

    private fun getImage(teamDetail: TeamDetail) {
        itemView.apply {
            teamDetail.logo?.let { imageViewTeam.loadUrl(it) }
            setOnClickListener {
                onItemClickTeam?.let { it -> it(teamDetail) }
            }
        }
    }

    fun registerTeamClickListener(onItemClickTeam: ((TeamDetail) -> Unit)?) {
        this.onItemClickTeam = onItemClickTeam
    }
}
