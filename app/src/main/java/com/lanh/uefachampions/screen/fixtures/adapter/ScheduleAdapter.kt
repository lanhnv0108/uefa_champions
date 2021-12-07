package com.lanh.uefachampions.screen.fixtures.adapter

import android.view.ViewGroup
import com.lanh.uefachampions.R
import com.lanh.uefachampions.databinding.ItemTeamMatchScheduleBinding
import com.lanh.uefachampions.databinding.ItemTitleScheduleBinding
import com.lanh.uefachampions.screen.base.BaseListAdapter
import com.lanh.uefachampions.screen.base.BaseViewHolder
import com.lanh.uefachampions.screen.item.ItemSchedule
import com.lanh.uefachampions.screen.item.ItemTeamMatchSchedule
import com.lanh.uefachampions.screen.item.ItemTextTitleSchedule
import com.lanh.uefachampions.utils.inflateView
import com.lanh.uefachampions.utils.safeClick

class ScheduleAdapter(private val onItemClick: (Int) -> Unit) :
    BaseListAdapter<ItemSchedule, ItemTeamMatchScheduleBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return when (viewType) {
            TYPE_TITLE -> TitleScheduleViewHolder(parent)
            TYPE_TEAM_MATCH -> ScheduleViewHolder(parent, onItemClick)
            else -> TitleScheduleViewHolder(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is ItemTeamMatchSchedule) {
            TYPE_TEAM_MATCH
        } else TYPE_TITLE
    }

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_TEAM_MATCH = 1
    }
}

class ScheduleViewHolder(parent: ViewGroup, private val onItemClick: (Int) -> Unit) :
    BaseViewHolder<ItemTeamMatchSchedule, ItemTeamMatchScheduleBinding>(
        inflateView(R.layout.item_team_match_schedule, parent)
    ) {
    init {
        binding.layoutItem.safeClick {
            onItemClick.invoke(binding.item?.id ?: -1)
        }
    }
}

class TitleScheduleViewHolder(parent: ViewGroup) :
    BaseViewHolder<ItemTextTitleSchedule, ItemTitleScheduleBinding>(
        inflateView(R.layout.item_title_schedule, parent)
    )
