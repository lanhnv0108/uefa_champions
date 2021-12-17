package com.lanh.uefachampions.screen.fixturedetail.adapter

import android.view.ViewGroup
import com.lanh.uefachampions.R
import com.lanh.uefachampions.databinding.ItemInfoFixtureDetailBinding
import com.lanh.uefachampions.screen.base.BaseListAdapter
import com.lanh.uefachampions.screen.base.BaseViewHolder
import com.lanh.uefachampions.screen.item.InfoFixtureDetailItem
import com.lanh.uefachampions.utils.inflateView

class InfoFixtureDetailAdapter :
    BaseListAdapter<InfoFixtureDetailItem, ItemInfoFixtureDetailBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return InfoFixtureDetailViewHolder(parent)
    }
}

class InfoFixtureDetailViewHolder(parent: ViewGroup) :
    BaseViewHolder<InfoFixtureDetailItem, ItemInfoFixtureDetailBinding>(
        inflateView(R.layout.item_info_fixture_detail, parent)
    )
