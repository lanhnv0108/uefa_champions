package com.lanh.uefachampions.screen.fixtures.adapter

import android.view.ViewGroup
import com.lanh.uefachampions.R
import com.lanh.uefachampions.databinding.ItemTeamMatchScheduleBinding
import com.lanh.uefachampions.databinding.ItemTitleScheduleBinding
import com.lanh.uefachampions.screen.base.BaseListAdapter
import com.lanh.uefachampions.screen.base.BaseViewHolder
import com.lanh.uefachampions.screen.base.ItemRecyclerview
import com.lanh.uefachampions.screen.base.NoInputItem
import com.lanh.uefachampions.screen.item.ItemTeamMatchSchedule
import com.lanh.uefachampions.screen.item.ItemTextTitleSchedule
import com.lanh.uefachampions.utils.inflateView
import com.lanh.uefachampions.utils.safeClick

class ScheduleAdapter(private val onItemClick: (ItemTeamMatchSchedule) -> Unit) :
    BaseListAdapter<ItemRecyclerview, ItemTeamMatchScheduleBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return when (viewType) {
            TYPE_TITLE -> TitleScheduleViewHolder(parent)
            TYPE_TEAM_MATCH -> ScheduleViewHolder(parent, onItemClick)
            else -> NoDataViewHolder(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is ItemTextTitleSchedule -> TYPE_TITLE
            is ItemTeamMatchSchedule -> TYPE_TEAM_MATCH
            is NoInputItem -> TYPE_NO_DATA
            else -> TYPE_NO_DATA
        }
    }

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_TEAM_MATCH = 1
        const val TYPE_NO_DATA = 2
    }
}

class ScheduleViewHolder(parent: ViewGroup, private val onItemClick: (ItemTeamMatchSchedule) -> Unit) :
    BaseViewHolder<ItemTeamMatchSchedule, ItemTeamMatchScheduleBinding>(
        inflateView(R.layout.item_team_match_schedule, parent)
    ) {
    init {
        binding.layoutItem.safeClick {
            binding.item?.let(onItemClick)
        }
    }
}

class TitleScheduleViewHolder(parent: ViewGroup) :
    BaseViewHolder<ItemTextTitleSchedule, ItemTitleScheduleBinding>(
        inflateView(R.layout.item_title_schedule, parent)
    )

class NoDataViewHolder(parent: ViewGroup) :
    BaseViewHolder<NoInputItem, ItemTitleScheduleBinding>(
        inflateView(R.layout.item_no_data, parent)
    )
