package com.lanh.uefachampions.screen.fixtures.adapter

import android.view.ViewGroup
import com.lanh.uefachampions.R
import com.lanh.uefachampions.databinding.ItemDatePickerBinding
import com.lanh.uefachampions.screen.base.BaseListAdapter
import com.lanh.uefachampions.screen.base.BaseViewHolder
import com.lanh.uefachampions.screen.item.ItemDate
import com.lanh.uefachampions.utils.inflateView

class DateAdapter(private val onItemClick: (ItemDate) -> Unit) :
    BaseListAdapter<ItemDate, ItemDatePickerBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return DateViewHolder(parent, onItemClick)
    }
}

class DateViewHolder(parent: ViewGroup, private val onItemClick: (ItemDate) -> Unit) :
    BaseViewHolder<ItemDate, ItemDatePickerBinding>(
        inflateView(R.layout.item_date_picker, parent)
    ) {
    init {
        binding.cardViewDate.setOnClickListener {
            binding.item?.let(onItemClick::invoke)
        }
    }
}
