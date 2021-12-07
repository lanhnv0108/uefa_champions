package com.lanh.uefachampions.screen.fixtures.adapter

import android.view.ViewGroup
import com.lanh.uefachampions.R
import com.lanh.uefachampions.databinding.ItemDateSeasonBinding
import com.lanh.uefachampions.screen.base.BaseListAdapter
import com.lanh.uefachampions.screen.base.BaseViewHolder
import com.lanh.uefachampions.screen.item.ItemDate
import com.lanh.uefachampions.utils.datetime.getListDateInMonth
import com.lanh.uefachampions.utils.inflateView
import org.joda.time.DateTime

class DateSeasonAdapter(private val itemDateSelected: (DateTime) -> Unit) :
    BaseListAdapter<ItemDate, ItemDateSeasonBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return DateSeasonViewHolder(parent, itemDateSelected)
    }
}

class DateSeasonViewHolder(parent: ViewGroup, private val itemDateSelected: (DateTime) -> Unit) :
    BaseViewHolder<ItemDate, ItemDateSeasonBinding>(
        inflateView(R.layout.item_date_season, parent)
    ) {
    private val adapter by lazy { DateAdapter(::itemDateClick) }
    private var positionDateChecked = 0

    init {
        binding.rcvDatePicker.adapter = adapter
    }

    override fun bind(item: ItemDate) {
        val dates = item.date.getListDateInMonth()
        adapter.submitList(dates) {
            binding.rcvDatePicker.scrollToPosition(dates.indexOf(item))
        }
    }

    private fun itemDateClick(itemDate: ItemDate) {
        itemDateSelected.invoke(itemDate.date)
        adapter.submitList(adapter.currentList.mapIndexed { index, item ->
            val isCheck = item == itemDate
            if (isCheck) positionDateChecked = index
            item.copy(isCheck = isCheck)
        }) {
            binding.rcvDatePicker.scrollToPosition(positionDateChecked)
        }
    }
}
