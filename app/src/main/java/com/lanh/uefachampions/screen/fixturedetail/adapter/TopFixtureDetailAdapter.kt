package com.lanh.uefachampions.screen.fixturedetail.adapter

import android.view.ViewGroup
import com.lanh.uefachampions.R
import com.lanh.uefachampions.databinding.ItemTopFixtureDetailBinding
import com.lanh.uefachampions.screen.base.BaseListAdapter
import com.lanh.uefachampions.screen.base.BaseViewHolder
import com.lanh.uefachampions.screen.item.TopFixtureDetailItem
import com.lanh.uefachampions.utils.inflateView

class TopFixtureDetailAdapter :
    BaseListAdapter<TopFixtureDetailItem, ItemTopFixtureDetailBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return TopFixtureDetailViewHolder(parent)
    }
}

class TopFixtureDetailViewHolder(parent: ViewGroup) :
    BaseViewHolder<TopFixtureDetailItem, ItemTopFixtureDetailBinding>(
        inflateView(R.layout.item_top_fixture_detail, parent)
    )
