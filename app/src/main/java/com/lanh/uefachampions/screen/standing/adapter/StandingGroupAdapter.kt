package com.lanh.uefachampions.screen.standing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.Standing
import com.lanh.uefachampions.data.model.StandingGroup
import com.lanh.uefachampions.data.model.Team
import com.lanh.uefachampions.databinding.ItemLayoutStandingBinding
import com.lanh.uefachampions.databinding.ItemLayoutStandingGroupBinding
import com.lanh.uefachampions.screen.base.BaseListAdapter
import com.lanh.uefachampions.screen.base.BaseViewHolder
import com.lanh.uefachampions.utils.OnItemRecyclerViewClickListener
import com.lanh.uefachampions.utils.inflateView
import kotlinx.android.synthetic.main.item_layout_standing_group.view.*

//
//class StandingGroupAdapter : RecyclerView.Adapter<StandingGroupAdapter.ViewHolder?>() {
//
//    private var standingGroups = mutableListOf<StandingGroup>()
//    private var listener: OnItemRecyclerViewClickListener<Team>? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_layout_standing_group, parent, false)
//        return ViewHolder(view, listener).apply {
//            bindSubAdapter()
//        }
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindViewData(standingGroups[position].standings)
//    }
//
//    override fun getItemCount() = standingGroups.size
//
//    fun updateData(standingGroups: MutableList<StandingGroup>?) {
//        standingGroups?.let {
//            this.standingGroups.clear()
//            this.standingGroups.addAll(it)
//            notifyDataSetChanged()
//        }
//    }
//
//    fun registerItemRecyclerViewClickListener(listener: OnItemRecyclerViewClickListener<Team>) {
//        this.listener = listener
//    }
//
//    class ViewHolder(
//        itemView: View,
//        private val itemListener: OnItemRecyclerViewClickListener<Team>?
//    ) : RecyclerView.ViewHolder(itemView) {
//
//        private val standingAdapter: StandingAdapter by lazy {
//            StandingAdapter()
//        }
//
//        fun bindSubAdapter() {
//            with(itemView.recyclerViewStanding) {
//                adapter = standingAdapter
//                setHasFixedSize(true)
//                setRecycledViewPool(RecyclerView.RecycledViewPool())
//            }
//            standingAdapter.registerItemViewHolderListener(itemListener)
//        }
//
//        fun bindViewData(standings: MutableList<Standing>?) {
//            standings?.let {
//                standingAdapter.updateData(it)
//                getGroup(it)
//            }
//        }
//
//        private fun getGroup(standings: MutableList<Standing>?) {
//            itemView.textViewTitleGroup.text = standings?.get(0)?.group
//        }
//    }
//}

class StandingGroupAdapter(private val onItemClick: (Team) -> Unit) :
    BaseListAdapter<StandingGroup, ItemLayoutStandingBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return StandingGroupViewHolder(parent, onItemClick)
    }
}

class StandingGroupViewHolder(parent: ViewGroup, private val onItemClick: (Team) -> Unit) :
    BaseViewHolder<StandingGroup, ItemLayoutStandingGroupBinding>(
        inflateView(R.layout.item_layout_standing_group, parent)
    ) {
    private val adapterStanding by lazy {
        StandingAdapter(onItemClick)
    }

    init {
        binding.rcvTeam.adapter = adapterStanding
    }

    override fun bind(item: StandingGroup) {
        super.bind(item)
        adapterStanding.submitList(item.standings)
    }
}