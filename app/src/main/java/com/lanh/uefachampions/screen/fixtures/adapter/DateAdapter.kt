package com.lanh.uefachampions.screen.fixtures.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.lanh.uefachampions.R
import com.lanh.uefachampions.databinding.ItemDatePickerBinding
import com.lanh.uefachampions.screen.base.BaseListAdapter
import com.lanh.uefachampions.screen.base.BaseViewHolder
import com.lanh.uefachampions.screen.item.ItemDate

class DateAdapter(private val onItemClick: (ItemDate) -> Unit) :
    BaseListAdapter<ItemDate, ItemDatePickerBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return DateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_date_picker,
                parent,
                false
            ), onItemClick
        )
    }
}

class DateViewHolder(itemView: ItemDatePickerBinding, private val onItemClick: (ItemDate) -> Unit) :
    BaseViewHolder<ItemDate, ItemDatePickerBinding>(itemView) {
    override fun bind(item: ItemDate) {
        super.bind(item)
        binding.item = item
        binding.cardViewDate.setOnClickListener {
            onItemClick.invoke(item)
        }
    }
}
